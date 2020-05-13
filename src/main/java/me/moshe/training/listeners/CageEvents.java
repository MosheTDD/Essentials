package me.moshe.training.listeners;

import me.moshe.training.Training;
import me.moshe.training.commands.Cage;
import me.moshe.training.commands.ChangeChatColor;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CageEvents implements Listener {
    private Training plugin;
    public CageEvents(Training plugin){ this.plugin = plugin; }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()){
            ArrayList<Location> locs = getCaged().get(uuid);
            p.teleport(locs.get(1));
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban %p% leaving while caged.".replaceAll("%p%", p.getName()));
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onPickUp(EntityPickupItemEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            UUID uuid = p.getUniqueId();
            if(getCaged().containsKey(uuid)){
                e.setCancelled(true);
            }
        }
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            UUID uuid = p.getUniqueId();
            if(getCaged().containsKey(uuid)){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e){
        if(!(e.getRightClicked() instanceof Player)) return;
        Player p = (Player) e.getRightClicked();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getCaged().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (!getCaged().containsKey(uuid)) return;
        e.setCancelled(true);
    }

    public HashMap<UUID, ArrayList<Location>> getCaged() { return Cage.caged; }
}
