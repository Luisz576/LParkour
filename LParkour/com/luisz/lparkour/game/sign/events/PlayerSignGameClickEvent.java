package com.luisz.lparkour.game.sign.events;

import com.lib576.plugin.event.CustomEvent;
import org.bukkit.entity.Player;

public class PlayerSignGameClickEvent extends CustomEvent {

    public final Player player;

    public PlayerSignGameClickEvent(Player player){
        //TODO:
        this.player = player;
    }

}