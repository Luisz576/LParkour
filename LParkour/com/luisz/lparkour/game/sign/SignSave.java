package com.luisz.lparkour.game.sign;

import org.bukkit.Location;

public class SignSave {

    public final int id;
    public final Location signLocation;
    public final String gameName;

    public SignSave(int id, String gameName, Location signLocation){
        this.id = id;
        this.signLocation = signLocation;
        this.gameName = gameName;
    }

}