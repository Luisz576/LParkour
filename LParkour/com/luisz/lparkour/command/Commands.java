package com.luisz.lparkour.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Você não pode usar esse comando!");
            return false;
        }
        Player p = (Player) sender;
        if(!p.isOp()){
            p.sendMessage(ChatColor.RED + "Você não tem permissão para utilizar esse comando!");
            return false;
        }
        switch (cmd.getName().toLowerCase()){
            case "lpc":
                //TODO:
                break;
            case "lpg":
                //TODO:
                break;
        }
        return false;
    }

}