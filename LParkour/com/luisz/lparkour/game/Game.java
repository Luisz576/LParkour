package com.luisz.lparkour.game;

import com.lib576.Lib576;
import com.luisz.lparkour.GamesController;
import com.luisz.lparkour.game.events.GamePlayEvent;
import com.luisz.lparkour.game.events.PlayerJoinGameEvent;
import com.luisz.lparkour.game.events.PlayerQuitGameEvent;
import com.luisz.lparkour.game.exception.GameWithTheSameNameAlreadyStarted;
import com.luisz.lparkour.game.listener.GameListener;
import com.luisz.lparkour.game.save.GameData;
import com.luisz.lparkour.game.save.GameLoader;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final GameData gameData;
    private final GameListener gameListener;
    private final int timerId;
    private GameState gameState = GameState.STARTING;

    private final List<GamePlayerProfile> players = new ArrayList<>();
    private final List<GamePlayerProfile> spectators = new ArrayList<>();

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

    public void destroyGame(){
        Lib576.sc.cancelTask(timerId);
        HandlerList.unregisterAll(this.gameListener);
        GamesController._destroyGame(this);
    }

    private void setupWorldGame(){
        //TODO:
    }

    public Game(GameLoader gameLoader) throws GameWithTheSameNameAlreadyStarted{
        this.gameData = gameLoader.loadData();
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

    }

    //Events
    public final void join(Player player){
        boolean isPlayer = false;
        if(getMaxPlayers() > players.size()) {
            isPlayer = true;
            _addPlayer(player);
        }else
            _addSpectator(player);
        Lib576.pm.callEvent(new PlayerJoinGameEvent(player, isPlayer, this));
    }
    private void _addPlayer(Player player){
        players.add(new GamePlayerProfile(player, this));
        player.setHealth(20);
        player.getInventory().clear();
        player.setLevel(0);
        player.setExp(0);
        player.setGameMode(GameMode.ADVENTURE);
        player.setFoodLevel(20);
        for(PotionEffect potionEffect : player.getActivePotionEffects())
            player.removePotionEffect(potionEffect.getType());
    }
    private void _addSpectator(Player player){
        spectators.add(new GamePlayerProfile(player, this));
        player.setHealth(20);
        player.getInventory().clear();
        player.setLevel(0);
        player.setExp(0);
        player.setGameMode(GameMode.SPECTATOR);
        player.setFoodLevel(20);
        for(PotionEffect potionEffect : player.getActivePotionEffects())
            player.removePotionEffect(potionEffect.getType());
    }

    public final void quit(Player player){
        boolean isPlayer = isPlayer(player);
        GamePlayerProfile p = getPlayerOrSpectator(player);
        players.remove(p);
        spectators.remove(p);
        Lib576.pm.callEvent(new PlayerQuitGameEvent(player, isPlayer, this));
    }

    public final void startGame(){
        //TODO:
        gameState = GameState.PLAYING;
        Lib576.callEvent(new GamePlayEvent(this));
    }

    public final void finishGame(GameStopReason reason){
        //TODO:
        gameState = GameState.STOPING;
        switch (reason){
            case ALL_PASSED:
                break;
            case FORCED:
                destroyGame();
                break;
        }
    }

    //Utils
    public final void sendMessageToAllPlayers(String message){
        for(GamePlayerProfile p : players)
            p.player.sendMessage(message);
        for(GamePlayerProfile p : spectators)
            p.player.sendMessage(message);
    }

}