package me.moshe.training.commands;

import me.moshe.training.Training;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Freezeplayer implements CommandExecutor {
    private Training plugin;
    public Freezeplayer(Training plugin){
        this.plugin = plugin;
        plugin.getCommand("freeze").setExecutor(this);
    }

    public static HashMap<UUID, Location> frozen = new HashMap<>();

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!sender.hasPermission("moshe.freeze")){
            sender.sendMessage("You don't have the permission to execute this command.");
            return true;
        }
        if(args.length != 1){
            sender.sendMessage("Not enough arguments.");
            return true;
        }
        Player t = Bukkit.getPlayerExact(args[0]);
        if(t == null){
            sender.sendMessage("The target is offline.");
            return true;
        }
        UUID uuid = t.getUniqueId();
        if(frozen.containsKey(uuid)){
            frozen.remove(uuid);
            t.sendMessage("You have been unfrozen.");
            sender.sendMessage("%t% has been unfrozen.".replaceAll("%t%", t.getName()));
            return true;
        }
        frozen.put(uuid, t.getLocation());
        t.sendMessage("You have been frozen! if you'll leave the server while frozen you will be punished!");
        sender.sendMessage("%t% has been frozen.".replaceAll("%t%", t.getName()));
        return false;
    }
}
