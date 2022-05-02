package com.luisz.lparkour.game.commons;

import org.bukkit.entity.Player;

public class GameScore {

    public final Player player;
    public final String gameName;
    public final int score;

    public GameScore(Player player, String gameName, int score) {
        this.player = player;
        this.gameName = gameName;
        this.score = score;
    }

}