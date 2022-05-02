package com.luisz.lparkour.game.events;

import com.lib576.plugin.event.CustomEvent;
import com.luisz.lparkour.game.Game;

public class GamePlayEvent extends CustomEvent {

    public final Game game;

    public GamePlayEvent(Game game){
        this.game = game;
    }

}