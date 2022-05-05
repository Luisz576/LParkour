package com.luisz.lparkour.game.commons;

import com.luisz.lparkour.game.Game;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GamePlayerProfile {

    public final Player player;
    public final Game game;
    public int score = 0;

    public Location lastCheckPoint = null;

    public GamePlayerProfile(Player player, Game game){
        this.player = player;
        this.game = game;
    }

    public final boolean isPlayer(){
        return game.isPlayer(player);
    }

}