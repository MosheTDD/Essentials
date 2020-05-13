package me.moshe.training.commands;

import me.moshe.training.Training;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.server.BroadcastMessageEvent;

public class Freezechat implements CommandExecutor {
    private Training plugin;
    public Freezechat(Training plugin){
        this.plugin = plugin;
        plugin.getCommand("freezechat").setExecutor(this);
    }
    public static boolean freeze = false;
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!sender.hasPermission("moshe.freezechat")){
            sender.sendMessage("You don't have the permission to execute this command.");
            return true;
        }
        if(args.length != 0){
            sender.sendMessage("Illegal arguments.");
            return true;
        }
        if(!freeze){
            freeze = true;
            sender.getServer().broadcastMessage("-----The chat has been frozen.------");
        }else{
            freeze = false;
            sender.getServer().broadcastMessage("-----The chat has been unfrozen.------");
        }
        return false;
    }
}
