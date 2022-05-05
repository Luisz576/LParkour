package com.luisz.lparkour.listener;

import com.lib576.Lib576;
import com.luisz.lparkour.controller.GamesController;
import com.luisz.lparkour.controller.SignsController;
import com.luisz.lparkour.game.Game;
import com.luisz.lparkour.game.sign.SignSave;
import com.luisz.lparkour.game.sign.events.PlayerSignGameClickEvent;
import com.luisz.lparkour.game.sign.events.SignsDataChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignsListener implements Listener {

    @EventHandler
    public void onSignGameIsClickedByPlayer(PlayerSignGameClickEvent e){
        Game game = GamesController.getGame(e.signSave.gameName);
        if(game != null){
            if(!GamesController.isInSomeGame(e.player)){
                e.player.sendMessage(ChatColor.YELLOW + "Entrando no game...");
                game.join(e.player, true);
            }else
                e.player.sendMessage(ChatColor.RED + "Você já está em um game!");
        }else
            e.player.sendMessage(ChatColor.RED + "Esse game está fechado!");
    }

    @EventHandler
    public void onSignsDataChange(SignsDataChangeEvent e){
        SignsController._reloadSigns();
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null){
            Location bl = e.getClickedBlock().getLocation();
            SignSave signSave = SignsController.getLikeSignSave(bl);
            if(signSave != null)
                Lib576.callEvent(new PlayerSignGameClickEvent(e.getPlayer(), signSave));
        }
    }

}