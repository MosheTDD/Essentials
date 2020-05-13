package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setspawn implements CommandExecutor {
    private Training plugin;
    public Setspawn(Training plugin){
        this.plugin = plugin;
        plugin.getCommand("setspawn").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only Players can execute this command.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("moshe.setspawn")){
            p.sendMessage(Utils.ucs("noPermission"));
            return true;
        }
        if(args.length != 0){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        Location spawn = p.getLocation();
        String world = spawn.getWorld().getName();
        double x = spawn.getX();
        double y = spawn.getY();
        double z = spawn.getZ();
        float yaw = spawn.getYaw();
        float pitch = spawn.getPitch();
        plugin.getConfig().set("spawn.world", world);
        plugin.getConfig().set("spawn.x", x);
        plugin.getConfig().set("spawn.y", y);
        plugin.getConfig().set("spawn.z", z);
        plugin.getConfig().set("spawn.yaw", yaw);
        plugin.getConfig().set("spawn.pitch", pitch);
        plugin.saveConfig();
        p.sendMessage(Utils.ucs("Setspawn.setSpawn"));
        return false;
    }
}
