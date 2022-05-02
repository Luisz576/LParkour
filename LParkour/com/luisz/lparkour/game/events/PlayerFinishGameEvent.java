package com.luisz.lparkour.game.events;

import com.lib576.plugin.event.CustomEvent;
import com.luisz.lparkour.game.Game;
import com.luisz.lparkour.game.commons.GamePlayerProfile;

public class PlayerFinishGameEvent extends CustomEvent {

    public final GamePlayerProfile playerProfile;
    public final Game game;

    public PlayerFinishGameEvent(GamePlayerProfile playerProfile, Game game){
        this.playerProfile = playerProfile;
        this.game = game;
    }

}