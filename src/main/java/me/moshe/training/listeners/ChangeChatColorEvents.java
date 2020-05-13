package me.moshe.training.listeners;

import me.moshe.training.Training;
import me.moshe.training.commands.ChangeChatColor;
import me.moshe.training.file.ChatColorData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;

public class ChangeChatColorEvents implements Listener {
    private Training plugin;
    public ChangeChatColorEvents(Training plugin) { this.plugin = plugin; }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(e.getMessage().startsWith("/")){
            return;
        }
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if(getColorMap().containsKey(uuid)){
            ChatColor color = ChatColor.valueOf(getColorMap().get(uuid));
            String msg = e.getMessage();
            e.setMessage(color + msg);
        }
    }



    public HashMap<UUID, String> getColorMap(){
        return ChatColorData.chatColors;
    }
}

