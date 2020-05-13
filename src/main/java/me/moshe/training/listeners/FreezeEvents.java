package me.moshe.training.listeners;

import me.moshe.training.Training;
import me.moshe.training.commands.Freezeplayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;

import java.util.HashMap;
import java.util.UUID;

public class FreezeEvents implements Listener {
    private Training plugin;
    public FreezeEvents(Training plugin){ this.plugin = plugin; }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()){
            p.teleport(getFrozen().get(uuid));
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban %p% leaving while caged.".replaceAll("%p%", p.getName()));
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onPickUp(EntityPickupItemEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            UUID uuid = p.getUniqueId();
            if(getFrozen().containsKey(uuid)){
                e.setCancelled(true);
            }
        }
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            UUID uuid = p.getUniqueId();
            if(getFrozen().containsKey(uuid)){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e){
        if(!(e.getRightClicked() instanceof Player)) return;
        Player p = (Player) e.getRightClicked();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(!getFrozen().containsKey(uuid)) return;
        e.setCancelled(true);
    }
    public HashMap<UUID, Location> getFrozen(){
        return Freezeplayer.frozen;
    }
}
