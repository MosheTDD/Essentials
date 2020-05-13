package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.listeners.CageEvents;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Cage implements CommandExecutor {
    private Training plugin;

    public Cage(Training plugin) {
        this.plugin = plugin;
        plugin.getCommand("cage").setExecutor(this);
    }

    public static HashMap<UUID, ArrayList<Location>> caged = new HashMap<>();

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!sender.hasPermission("moshe.cage")) {
            sender.sendMessage(Utils.ucs("noPermission"));
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(Utils.ucs("notEnoughArgs"));
            return true;
        }
        Player t = Bukkit.getPlayerExact(args[0]);
        if (t == null) {
            sender.sendMessage(Utils.ucs("offlinePlayer"));
            return true;
        }
        UUID uuid = t.getUniqueId();
        if(caged.containsKey(uuid)){
            ArrayList<Location> locs = caged.get(uuid);
            Location loc = t.getLocation();
            ArrayList<Block> cage = getNearbyBlocks(loc, 1);
            for(Block block: cage){
                block.setType(Material.AIR);
            }
            t.teleport(locs.get(0));
            caged.remove(uuid);
            t.sendMessage(Utils.ucs("Cage.uncaged"));
            sender.sendMessage(Utils.ucs("Cage.targetUncaged").replaceAll("%t%", t.getName()));
            return true;
        }
        ArrayList<Location> locs= new ArrayList<>();
        locs.add(t.getLocation());
        t.sendMessage(Utils.ucs("Cage.caged"));
        sender.sendMessage(Utils.ucs("Cage.targetCaged").replaceAll("%t%", t.getName()));
        Location barrierPlacement = t.getLocation();
        String world = barrierPlacement.getWorld().getName();
        double x = barrierPlacement.getX();
        double y = barrierPlacement.getY();
        double z = barrierPlacement.getZ();
        float yaw = barrierPlacement.getYaw();
        float pitch = barrierPlacement.getPitch();
        Location newY = new Location(Bukkit.getWorld(world), x, y+10, z, yaw, pitch);
        ArrayList<Block> cage = getNearbyBlocks(newY, 1);
        for(Block block: cage){
            block.setType(Material.GLASS);
        }
        newY.getBlock().setType(Material.AIR);
        t.teleport(newY);
        locs.add(t.getLocation());
        caged.put(uuid, locs);


        return false;
    }

    public ArrayList<Block> getNearbyBlocks(Location location, int radius) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}