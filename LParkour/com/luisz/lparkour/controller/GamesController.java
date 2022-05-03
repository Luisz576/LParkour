package com.luisz.lparkour.controller;

import com.luisz.lparkour.game.Game;
import org.bukkit.entity.Player;

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

    public static boolean isInSomeGame(Player player){
        for(Game game : games)
            if(game.isPlayer(player)) //Não há necessidade de ver se é um spectator
                return true;
        return false;
    }

    public static void _destroyGame(Game game){
        games.remove(game);
    }

}