package com.luisz.lparkour.game.save;

import com.lib576.utils.Area;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    public final String gameName;

    public int maxPlayers;
    public Location waitLocation, startLocation;
    public List<Location> checkpoints = new ArrayList<>();
    public Area finishLine;

    public GameData(String gameName){
        this.gameName = gameName;
    }

}