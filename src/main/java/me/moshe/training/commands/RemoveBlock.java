package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.util.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;

public class RemoveBlock implements CommandExecutor {
    private Training plugin;

    public RemoveBlock(Training plugin) {
        this.plugin = plugin;
        plugin.getCommand("remove").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(Utils.ucs("notAPlayer"));
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("moshe.remove")){
            p.sendMessage(Utils.ucs("noPermission"));
            return true;
        }
        if(args.length != 2){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        try {
            Material mat = Material.valueOf(args[0].toUpperCase());
            int radius = Integer.parseInt(args[1]);
            Location loc = p.getLocation();
            ArrayList<Block> clear = getNearbyBlocks(loc, radius, mat);
            for(Block block: clear){
                block.setType(Material.AIR);
            }
            p.sendMessage(Utils.ucs("RemoveBlock.done"));

        }catch (IllegalArgumentException ex){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }


        return false;
    }
    public ArrayList<Block> getNearbyBlocks(Location location, int radius, Material material) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    if(location.getWorld().getBlockAt(x,y,z).getType() == material ){
                        blocks.add(location.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }
}
