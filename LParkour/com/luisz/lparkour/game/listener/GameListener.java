package com.luisz.lparkour.game.listener;

import com.luisz.lparkour.game.events.*;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameListener implements Listener {

    private final String gameName;

    public GameListener(String gameName){
        this.gameName = gameName;
    }

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinGameEvent e){
        if(e.game.getGameName().equalsIgnoreCase(gameName)) {
            if (e.isPlayer)
                e.game.sendMessageToAllPlayers(ChatColor.YELLOW + e.player.getName() + ChatColor.GREEN + " entrou!");
            else
                e.game.sendMessageToAllPlayers(ChatColor.RED + e.player.getName() + ChatColor.DARK_GRAY + " está espectando!");
        }
    }

    @EventHandler
    public void onPlayerQuitGame(PlayerQuitGameEvent e){
        if(e.game.getGameName().equalsIgnoreCase(gameName)) {
            if (e.isPlayer)
                e.game.sendMessageToAllPlayers(ChatColor.YELLOW + e.player.getName() + ChatColor.RED + " saiu!");
            else
                e.game.sendMessageToAllPlayers(ChatColor.RED + e.player.getName() + ChatColor.DARK_GRAY + " não está mais espectando!");
        }
    }

    @EventHandler
    public void onGamePlay(GamePlayEvent e){
        //TODO:
    }

    @EventHandler
    public void onPlayerGetCheckpoint(PlayerGetCheckpointEvent e){
        //TODO:
    }

    @EventHandler
    public void onPlayerFinishGame(PlayerFinishGameEvent e) {
        //TODO:
    }

}