package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MsgColor implements CommandExecutor {
    private Training plugin;

    public MsgColor(Training plugin) {
        this.plugin = plugin;
        plugin.getCommand("color").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players may execute this command.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("moshe.color.msgcolor")){
            p.sendMessage(Utils.ucs("noPermission"));
            return true;
        }
        if(args.length != 2){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        try {
            ChatColor color = changeColor(args[0]);
            args[0] = "";
            StringBuilder msg = new StringBuilder();
            for(String s: args){
                msg.append(s).append(" ");
            }
            p.chat(color + String.valueOf(msg));
        }catch (IllegalArgumentException ex){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        return false;
    }
    public ChatColor changeColor(String color){
        return ChatColor.valueOf(color.toUpperCase());
    }
}
