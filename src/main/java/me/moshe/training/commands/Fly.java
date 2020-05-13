package me.moshe.training.commands;

import me.moshe.training.Training;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    private Training plugin;
    public Fly (Training plugin){
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("moshe.fly")){
            p.sendMessage("No permission");
            return true;
        }
        if(args.length == 0){
            if(p.getAllowFlight()){
                p.setAllowFlight(false);
                p.sendMessage("You can't fly now.");
            }else {
                p.setAllowFlight(true);
                p.sendMessage("You can fly now.");
            }
            return true;
        }
        if(args.length == 1){
            if(!p.hasPermission("moshe.fly.other")){
                p.sendMessage("You don't have permission to execute this command.");
                return true;
            }
            Player t = Bukkit.getPlayerExact(args[0]);
            if(t == null){
                p.sendMessage("Target offline");
                return true;
            }
            if(t.getAllowFlight()){
                t.setAllowFlight(false);
                t.sendMessage("You can't fly now.");
                p.sendMessage("You have disabled flight for %t%".replaceAll("%t%", t.getName()));
            }else{
                t.setAllowFlight(true);
                t.sendMessage("You can fly now.");
                p.sendMessage("You have enabled flight for %t%".replaceAll("%t%", t.getName()));
            }
        }else{
            p.sendMessage("Illegal arguments.");
        }
        return false;
    }
}
