package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    private Training plugin;
    public Spawn(Training plugin){
        this.plugin = plugin;
        plugin.getCommand("spawn").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only Players can execute this command.");
            return true;
        }
        Player p = (Player) sender;
        Location spawn = getSpawn();
        if (args.length == 0) {
            if (!p.hasPermission("moshe.spawn")) return true;
            p.teleport(spawn);
            p.sendMessage(Utils.ucs("Spawn.tpSpawn"));
            return true;
        }
        if(args.length == 1){
            if(!p.hasPermission("moshe.spawn.other")) return true;
            Player t = Bukkit.getPlayerExact(args[0]);
            if(t == null){
                p.sendMessage(Utils.ucs("offlinePlayer").replaceAll("%t%", args[0]));
                return true;
            }
            t.teleport(spawn);
            t.sendMessage(Utils.ucs("Spawn.tpSpawn"));
            p.sendMessage(Utils.ucs("Spawn.other").replaceAll("%t%", t.getName()));
            return true;
        }else {
            p.sendMessage(Utils.ucs("ilg"));
        }


        return false;
    }

    public Location getSpawn(){
        World world = Bukkit.getWorld(Utils.cs("spawn.world"));
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }
}
