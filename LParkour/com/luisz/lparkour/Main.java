package com.luisz.lparkour;

import com.lib576.Lib576;
import com.lib576.plugin.LPlugin;
import com.luisz.lparkour.command.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public class Main extends LPlugin {

    private static CommandExecutor commandExecutor = null;

    @Override
    public void enable() {
        loadCommand("lpc");
        loadCommand("lpg");
        Lib576.cmd.sendMessage(ChatColor.GREEN + "LParkour loaded!");
    }

    private void loadCommand(String commandName){
        if(commandExecutor == null)
            commandExecutor = new Commands();
        Objects.requireNonNull(getCommand(commandName)).setExecutor(commandExecutor);
    }

}