package com.luisz.lparkour.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GamePlayerProfile {

    public final Player player;
    public final Game game;

    public Location lastCheckPoint = null;

    public GamePlayerProfile(Player player, Game game){
        this.player = player;
        this.game = game;
    }

    public final boolean isPlayer(){
        return game.isPlayer(player);
    }

}