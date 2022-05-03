package com.luisz.lparkour.game.sign;

import org.bukkit.Location;

public class SignSave {

    public final Location signLocation;
    public final String gameName;

    public SignSave(Location signLocation, String gameName){
        this.signLocation = signLocation;
        this.gameName = gameName;
    }

}