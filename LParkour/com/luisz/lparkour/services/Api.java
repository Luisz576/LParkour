package com.luisz.lparkour.services;

import com.lib576.web.HttpRequest;
import com.luisz.lparkour.game.commons.GamePlayerProfile;
import org.bukkit.entity.Player;

public class Api {

    private static String SERVER_URL = "";

    public static void _startApi(){
        ApiConfig apiConfig = new ApiConfig();
        SERVER_URL = apiConfig.getApiBase();
    }

    public static void setPlayerScoreInGame(GamePlayerProfile gameScore){
        HttpRequest request = new HttpRequest(SERVER_URL);
        request.setParameter("player_uuid", gameScore.player.getUniqueId().toString());
        request.setParameter("game_name", gameScore.game.getGameName());
        request.setParameter("score", "" + gameScore.score);
        request.getJsonFromUrl(); //Do request
    }

    public static int getScoreFromPlayerAndGame(Player player, String gameName){
        //TODO: GET SCORE
        return -1;
    }

}