package com.luisz.lparkour.services;

import com.lib576.web.HttpRequest;
import com.luisz.lparkour.game.commons.GameScore;
import org.bukkit.entity.Player;

public class Api {

    public static final String SERVER_URL = "";

    public static void setPlayerScoreInGame(GameScore gameScore){
        HttpRequest request = new HttpRequest(SERVER_URL);
        request.setParameter("player_uuid", gameScore.player.getUniqueId().toString());
        request.setParameter("game_name", gameScore.gameName);
        request.setParameter("score", "" + gameScore.score);
        request.getJsonFromUrl(); //Do request
    }

    public static GameScore getScoreFromPlayerAndGame(Player player, String gameName){
        //TODO: GET SCORE
        return null;
    }

}