package com.luisz.lparkour.game.events;

import com.lib576.plugin.event.CustomEvent;
import com.luisz.lparkour.game.Game;
import org.bukkit.entity.Player;

public class PlayerQuitGameEvent extends CustomEvent {

    public final Player player;
    public final boolean isPlayer;
    public final Game game;

    public PlayerQuitGameEvent(Player player, boolean isPlayer, Game game){
        this.player = player;
        this.isPlayer = isPlayer;
        this.game = game;
    }

}