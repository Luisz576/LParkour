package com.luisz.lparkour.game.events;

import com.lib576.plugin.event.CustomEvent;
import com.luisz.lparkour.game.Game;
import com.luisz.lparkour.game.GamePlayerProfile;
import org.bukkit.Location;

public class PlayerGetCheckpointEvent extends CustomEvent {

    public final GamePlayerProfile playerProfile;
    public final Location checkpoint;
    public final Game game;

    public PlayerGetCheckpointEvent(GamePlayerProfile playerProfile, Location checkpoint, Game game){
        this.playerProfile = playerProfile;
        this.checkpoint = checkpoint;
        this.game = game;
    }

}