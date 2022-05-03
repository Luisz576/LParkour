package com.luisz.lparkour.game.sign.events;

import com.lib576.plugin.event.CustomEvent;
import com.luisz.lparkour.game.sign.SignSave;
import org.bukkit.entity.Player;

public class PlayerSignGameClickEvent extends CustomEvent {

    public final Player player;
    public final SignSave signSave;

    public PlayerSignGameClickEvent(Player player, SignSave signSave){
        this.player = player;
        this.signSave = signSave;
    }

}