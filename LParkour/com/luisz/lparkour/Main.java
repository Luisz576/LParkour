package com.luisz.lparkour;

import com.lib576.Lib576;
import com.lib576.plugin.LPlugin;
import com.luisz.lparkour.command.Commands;
import com.luisz.lparkour.game.sign.SignsSave;
import com.luisz.lparkour.listener.SignsListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public class Main extends LPlugin {

    public static SignsSave signsSave = null;
    private static CommandExecutor commandExecutor = null;

    @Override
    public void enable() {
        signsSave = new SignsSave();
        loadCommand("lpc");
        loadCommand("lpg");
        Lib576.pm.registerEvents(new SignsListener(), Lib576.getInstance());
        Lib576.cmd.sendMessage(ChatColor.GREEN + "LParkour loaded!");
    }

    private void loadCommand(String commandName){
        if(commandExecutor == null)
            commandExecutor = new Commands();
        Objects.requireNonNull(getCommand(commandName)).setExecutor(commandExecutor);
    }

}