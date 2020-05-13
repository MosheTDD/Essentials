package me.moshe.training.listeners;

import me.moshe.training.Training;
import me.moshe.training.commands.Freezechat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

public class FreezechatEvents implements Listener {
    private Training plugin;
    public FreezechatEvents(Training plugin){ this.plugin = plugin; }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("moshe.bypassfreeze")) return;

        if(Freezechat.freeze){
            e.setCancelled(true);
            p.sendMessage("The chat is frozen at the moment.");
        }

    }


}
