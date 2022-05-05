package com.luisz.lparkour.game;

import com.lib576.Lib576;
import com.lib576.utils.Area;
import com.luisz.lparkour.controller.GamesController;
import com.luisz.lparkour.controller.SignsController;
import com.luisz.lparkour.game.commons.GamePlayerProfile;
import com.luisz.lparkour.game.commons.GameState;
import com.luisz.lparkour.game.commons.GameStopReason;
import com.luisz.lparkour.game.events.GamePlayEvent;
import com.luisz.lparkour.game.events.PlayerJoinGameEvent;
import com.luisz.lparkour.game.events.PlayerQuitGameEvent;
import com.luisz.lparkour.game.exception.GameWithTheSameNameAlreadyStarted;
import com.luisz.lparkour.game.listener.GameListener;
import com.luisz.lparkour.game.save.GameData;
import com.luisz.lparkour.game.save.GameLoader;
import com.luisz.lparkour.game.score.LParkourScoreboard;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final GameData gameData;
    private final GameListener gameListener;
    private final int timerId;

    private int time = 0;
    private GameState gameState = GameState.STARTING;
    private LParkourScoreboard scoreboard;

    public final int getTime(){ return this.time; }
    public final GameState getGameState(){ return this.gameState; }

    private final List<GamePlayerProfile> players = new ArrayList<>();
    private final List<GamePlayerProfile> spectators = new ArrayList<>();

    public final List<GamePlayerProfile> getAllPlayersAndSpectators(){
        List<GamePlayerProfile> playersAndSpectators = new ArrayList<>();
        playersAndSpectators.addAll(players);
        playersAndSpectators.addAll(spectators);
        return playersAndSpectators;
    }

    public final GamePlayerProfile getPlayerOrSpectator(Player player){
        for(GamePlayerProfile p : players)
            if(p.player.getName().equals(player.getName()))
                return p;
        for(GamePlayerProfile p : spectators)
            if(p.player.getName().equals(player.getName()))
                return p;
        return null;
    }

    public final boolean isPlayer(Player player){
        for(GamePlayerProfile p : players)
            if(p.player.getName().equals(player.getName()))
                return true;
        return false;
    }

    //GameInfo
    public final String getGameName(){ return this.gameData.gameName; }
    public final int getMaxPlayers(){ return this.gameData.maxPlayers; }
    public final int getAmountOfPlayers(){ return this.players.size(); }
    public final Location getStartLocation(){ return this.gameData.startLocation; }
    public final Location getWaitLocation(){ return this.gameData.waitLocation; }
    public final List<Location> getCheckpoints(){ return this.gameData.checkpoints; }
    public final Area getFinishLine(){ return this.gameData.finishLine; }

    public void destroyGame(){
        players.clear();
        spectators.clear();
        Lib576.sc.cancelTask(timerId);
        HandlerList.unregisterAll(this.gameListener);
        GamesController._destroyGame(this);
    }

    private void setupWorldGame(){
        //TODO:
    }

    public Game(GameLoader gameLoader) throws GameWithTheSameNameAlreadyStarted{
        this.gameData = gameLoader.loadData();
        this.scoreboard = new LParkourScoreboard(this);
        if(GamesController.getGame(getGameName()) != null){
            throw new GameWithTheSameNameAlreadyStarted();
        }else{
            this.gameListener = new GameListener(getGameName());
            Lib576.pm.registerEvents(this.gameListener, Lib576.getInstance());
            GamesController._registerNewGame(this);
            setupWorldGame();
            this.timerId = Lib576.sc.scheduleSyncDelayedTask(Lib576.getInstance(), this::run, 20L);
        }
    }

    //GAME LOOP
    private void run(){
        if(gameState == GameState.PLAYING){
            this.scoreboard._updateScoreboard();
            time++;
        }
    }

    //Events
    public final void join(Player player, boolean callEvent){
        boolean isPlayer = false;
        if(gameState == GameState.STARTING && getMaxPlayers() > players.size()) {
            isPlayer = true;
            _addPlayer(player);
        }else
            _addSpectator(player);
        player.setHealth(20);
        player.getInventory().clear();
        player.setLevel(0);
        player.setExp(0);
        player.setFoodLevel(20);
        for(PotionEffect potionEffect : player.getActivePotionEffects())
            player.removePotionEffect(potionEffect.getType());
        if(gameState == GameState.STARTING)
            player.teleport(getWaitLocation());
        else
            player.teleport(getStartLocation());
        if(callEvent)
            Lib576.pm.callEvent(new PlayerJoinGameEvent(player, isPlayer, this));
        SignsController._updateSigns();
    }
    private void _addPlayer(Player player){
        players.add(new GamePlayerProfile(player, this));
        player.setGameMode(GameMode.ADVENTURE);
        this.scoreboard._updateScoreboard();
    }
    private void _addSpectator(Player player){
        spectators.add(new GamePlayerProfile(player, this));
        player.setGameMode(GameMode.SPECTATOR);
        this.scoreboard._updateScoreboard();
    }

    public final void quit(Player player){
        boolean isPlayer = isPlayer(player);
        GamePlayerProfile p = getPlayerOrSpectator(player);
        players.remove(p);
        spectators.remove(p);
        Lib576.pm.callEvent(new PlayerQuitGameEvent(player, isPlayer, this));
        SignsController._updateSigns();
        this.scoreboard._updateScoreboard();
    }

    public final void startGame(){
        //TODO:
        time = 0;
        gameState = GameState.PLAYING;
        Lib576.callEvent(new GamePlayEvent(this));
        SignsController._updateSigns();
        this.scoreboard._updateScoreboard();
    }

    public final void finishGame(GameStopReason reason){
        //TODO:
        gameState = GameState.STOPING;
        SignsController._updateSigns();
        switch (reason){
            case ALL_PASSED:
                break;
            case FORCED:
                destroyGame();
                break;
        }
        SignsController._updateSigns();
        this.scoreboard._updateScoreboard();
    }

    //Utils
    public final void sendMessageToAllPlayers(String message){
        for(GamePlayerProfile p : players)
            p.player.sendMessage(message);
        for(GamePlayerProfile p : spectators)
            p.player.sendMessage(message);
    }

}