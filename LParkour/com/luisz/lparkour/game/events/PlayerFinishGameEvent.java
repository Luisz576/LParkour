package com.luisz.lparkour.game.events;

import com.lib576.plugin.event.CustomEvent;
import com.luisz.lparkour.game.Game;
import com.luisz.lparkour.game.GamePlayerProfile;

public class PlayerFinishGameEvent extends CustomEvent {

    public final GamePlayerProfile playerProfile;
    public final int time;
    public final Game game;

    public PlayerFinishGameEvent(GamePlayerProfile playerProfile, int time, Game game){
        this.playerProfile = playerProfile;
        this.time = time;
        this.game = game;
    }

}