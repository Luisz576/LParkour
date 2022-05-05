package com.luisz.lparkour.game.listener;

import com.lib576.Lib576;
import com.lib576.utils.LConvert;
import com.luisz.lparkour.controller.GamesController;
import com.luisz.lparkour.game.Game;
import com.luisz.lparkour.game.commons.GamePlayerProfile;
import com.luisz.lparkour.game.commons.GameState;
import com.luisz.lparkour.game.events.*;
import com.luisz.lparkour.services.Api;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class GameListener implements Listener {

    private final String gameName;

    public GameListener(String gameName){
        this.gameName = gameName;
    }

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinGameEvent e){
        if(e.game.getGameName().equals(gameName)) {
            if (e.isPlayer)
                e.game.sendMessageToAllPlayers(ChatColor.YELLOW + e.player.getName() + ChatColor.GREEN + " entrou!");
            else
                e.game.sendMessageToAllPlayers(ChatColor.RED + e.player.getName() + ChatColor.DARK_GRAY + " está espectando!");
        }
    }

    @EventHandler
    public void onPlayerQuitGame(PlayerQuitGameEvent e){
        if(e.game.getGameName().equals(gameName)) {
            if (e.isPlayer)
                e.game.sendMessageToAllPlayers(ChatColor.YELLOW + e.player.getName() + ChatColor.RED + " saiu!");
            else
                e.game.sendMessageToAllPlayers(ChatColor.RED + e.player.getName() + ChatColor.DARK_GRAY + " não está mais espectando!");
        }
    }

    @EventHandler
    public void onGamePlay(GamePlayEvent e){
        if(e.game.getGameName().equals(gameName))
            for(GamePlayerProfile p : e.game.getAllPlayersAndSpectators())
                p.player.teleport(e.game.getStartLocation());
    }

    @EventHandler
    public void onPlayerMissesParkour(PlayerMissesParkourEvent e){
        if(e.game.getGameName().equals(gameName)){
            if(e.playerProfile.lastCheckPoint != null)
                e.playerProfile.player.teleport(e.playerProfile.lastCheckPoint);
            else
                e.playerProfile.player.teleport(e.game.getStartLocation());
        }
    }

    @EventHandler
    public void onPlayerGetCheckpoint(PlayerGetCheckpointEvent e){
        if(e.game.getGameName().equals(gameName))
            if(e.game.getGameState() == GameState.PLAYING) {
                e.playerProfile.lastCheckPoint = e.checkpoint;
                e.playerProfile.player.sendMessage(ChatColor.YELLOW + "Novo Checkpoint!");
            }
    }

    @EventHandler
    public void onPlayerFinishGame(PlayerFinishGameEvent e) {
        if(e.game.getGameName().equals(gameName)){
            e.playerProfile.score = e.game.getTime();
            Api.setPlayerScoreInGame(e.playerProfile);
            e.game.sendMessageToAllPlayers(ChatColor.YELLOW + "O jogador " + ChatColor.GREEN + e.playerProfile.player.getName() + ChatColor.YELLOW + " terminou em " + e.playerProfile.score + "s");
            e.game.join(e.playerProfile.player, false);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Game game = GamesController.getGame(gameName);
        if(game != null && game.getGameState() == GameState.PLAYING && game.isPlayer(e.getPlayer())){
            Location checkpoint = getIfIsNextToSomeCheckpoint(game.getCheckpoints(), e.getPlayer().getLocation());
            if(checkpoint != null)
                Lib576.callEvent(new PlayerGetCheckpointEvent(game.getPlayerOrSpectator(e.getPlayer()), checkpoint, game));
            if(game.getFinishLine().isInside(e.getPlayer().getLocation()))
                Lib576.callEvent(new PlayerFinishGameEvent(game.getPlayerOrSpectator(e.getPlayer()), game));
        }
    }

    private Location getIfIsNextToSomeCheckpoint(List<Location> checkpoints, Location location){
        for(Location checkpoint : checkpoints){
            int disX = LConvert.module(location.getBlockX() - checkpoint.getBlockX());
            if(disX <= 1) {
                int disY = LConvert.module(location.getBlockY() - checkpoint.getBlockY());
                if(disY <= 1) {
                    int disZ = LConvert.module(location.getBlockZ() - checkpoint.getBlockZ());
                    if(disZ <= 1)
                        return checkpoint;
                }
            }
        }
        return null;
    }

}