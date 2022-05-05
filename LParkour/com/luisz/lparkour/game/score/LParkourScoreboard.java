package com.luisz.lparkour.game.score;

import com.lib576.player.scoreboard.LScoreboard;
import com.luisz.lparkour.game.Game;
import org.bukkit.entity.Player;

public class LParkourScoreboard {

    private final LScoreboard lScoreboard;
    private final Game game;

    public LParkourScoreboard(Game game){
        this.lScoreboard = new LScoreboard();
        this.game = game;
        _updateScoreboard();
    }

    public void renderToPLayer(Player player){
        lScoreboard.renderScoreToPlayer(player);
    }

    public void _updateScoreboard(){
        //TODO: UPDATE
    }

}