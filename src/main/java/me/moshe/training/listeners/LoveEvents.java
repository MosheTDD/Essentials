package me.moshe.training.listeners;

import me.moshe.training.Training;
import me.moshe.training.commands.Love;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class LoveEvents implements Listener {
    private Training plugin;
    public LoveEvents(Training plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        ArrayList<UUID> loveList = Love.loveList;
        if(loveList.contains(uuid)){
            Block block = p.getLocation().getBlock();
            Location loc = new Location(block.getWorld(), block.getX(), block.getY()-1, block.getZ());
            loc.getBlock().setType(Material.PURPLE_TERRACOTTA, true);
            World world = loc.getWorld();
            ItemStack rose = new ItemStack(Material.ROSE_BUSH);
            ItemMeta roseM = rose.getItemMeta();
            roseM.setDisplayName("loveRose");
            rose.setItemMeta(roseM);
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> world.dropItemNaturally(p.getLocation(), rose), 15L);
        }
    }
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        ArrayList<UUID> loveList = Love.loveList;
        if(loveList.contains(uuid)){
            if(e.getItem().getItemStack().getItemMeta() == null) return;
            if(e.getItem().getItemStack().getType() == Material.ROSE_BUSH && e.getItem().getItemStack().getItemMeta().getDisplayName().equals("loveRose")){
                e.setCancelled(true);
            }
        }
    }

}
