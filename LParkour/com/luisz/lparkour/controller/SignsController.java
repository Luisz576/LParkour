package com.luisz.lparkour.controller;

import com.luisz.lparkour.Main;
import com.luisz.lparkour.game.Game;
import com.luisz.lparkour.game.sign.SignSave;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;

import java.util.ArrayList;
import java.util.List;

public class SignsController {

    private static final List<SignSave> signsSaves = new ArrayList<>();

    public static void _reloadSigns(){
        signsSaves.clear();
        signsSaves.addAll(Main.signsSave.loadSigns());
        _updateSigns();
    }

    public static void _updateSigns(){
        for(SignSave signSave : signsSaves){
            if(signSave.signLocation.getBlock().getType() == Material.OAK_SIGN ||
                signSave.signLocation.getBlock().getType() == Material.OAK_WALL_SIGN){
                Sign sign = (Sign) signSave.signLocation.getBlock();
                Game game = GamesController.getGame(signSave.gameName);
                sign.setLine(1, signSave.gameName);
                if(game != null) {
                    switch (game.getGameState()){
                        case STARTING:
                            sign.setLine(0, ChatColor.GREEN + "[RECRUTANDO]");
                            break;
                        case STOPING:
                            sign.setLine(0, ChatColor.RED + "[FINALIZANDO]");
                            break;
                        case PLAYING:
                            sign.setLine(0, ChatColor.YELLOW + "[EM PARTIDA]");
                            break;
                    }
                    sign.setLine(2, ChatColor.BLACK + "" + game.getAmountOfPlayers() + "/" + game.getMaxPlayers());
                }else {
                    sign.setLine(0, ChatColor.RED + "[FECHADO]");
                    sign.setLine(2, ChatColor.BLACK + "0/0");
                }
            }
        }
    }

    public static SignSave getLikeSignSave(Location signLocation){
        for(SignSave signSave : signsSaves){
            if(signSave.signLocation.getBlockX() == signLocation.getBlockX()
                && signSave.signLocation.getBlockY() == signLocation.getBlockY()
                && signSave.signLocation.getBlockZ() == signLocation.getBlockZ())
                return signSave;
        }
        return null;
    }

}