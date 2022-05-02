package com.luisz.lparkour;

import com.luisz.lparkour.game.Game;

import java.util.ArrayList;
import java.util.List;

public class GamesController {

    private static final List<Game> games = new ArrayList<>();

    public static void _registerNewGame(Game game){
        if(!games.contains(game))
            games.add(game);
    }

    public static Game getGame(String gameName){
        for(Game game : games)
            if(game.getGameName().equalsIgnoreCase(gameName.toLowerCase()))
                return game;
        return null;
    }

    public static void _destroyGame(Game game){
        games.remove(game);
    }

}