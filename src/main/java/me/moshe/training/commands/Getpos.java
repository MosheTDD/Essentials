package me.moshe.training.commands;

import me.moshe.training.Training;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Getpos implements CommandExecutor{
    private Training plugin;
    public Getpos(Training plugin){
        this.plugin = plugin;
        plugin.getCommand("getpos").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!sender.hasPermission("moshe.getpos")){
            sender.sendMessage("You don't have permission");
            return true;
        }
        if(args.length != 1){
            sender.sendMessage("Illegal arguments");
            return true;
        }
        Player t = Bukkit.getPlayerExact(args[0]);
        if(t == null){
            sender.sendMessage("Target is offline.");
            return true;
        }
        Location loc = t.getLocation();
        long x = Math.round(loc.getX());
        long y = Math.round(loc.getY());
        long z = Math.round(loc.getZ());
        String world = loc.getWorld().getName();
        boolean flight = t.getAllowFlight();
        boolean op = t.isOp();
        GameMode gameMode = t.getGameMode();
        sender.sendMessage("------%t%------".replaceAll("%t%", t.getName()));
        sender.sendMessage("OP: %op%".replaceAll("%op%", String.valueOf(op).toUpperCase()));
        sender.sendMessage("Flight: %flight%".replaceAll("%flight%", String.valueOf(flight).toUpperCase()));
        sender.sendMessage("GameMode: %gameMode%".replaceAll("%gameMode%", String.valueOf(gameMode).toUpperCase()));
        sender.sendMessage("World: %world%".replaceAll("%world%", world));
        sender.sendMessage("X: %x%".replaceAll("%x%", String.valueOf(x)));
        sender.sendMessage("Y: %y%".replaceAll("%y%", String.valueOf(y)));
        sender.sendMessage("Z: %z%".replaceAll("%z%", String.valueOf(z)));
        sender.sendMessage("-----------");
        return false;
    }
}
