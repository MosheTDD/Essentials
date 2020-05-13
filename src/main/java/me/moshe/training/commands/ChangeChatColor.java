package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.file.ChatColorData;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ChangeChatColor implements CommandExecutor {
    private final Training plugin;

    public ChangeChatColor(Training plugin) {
        this.plugin = plugin;
        plugin.getCommand("chatcolor").setExecutor(this);
    }


    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players may execute this command.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("moshe.chat.chatcolor")){
            p.sendMessage(Utils.ucs("noPermission"));
            return true;
        }
        if(args.length != 1){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        try {
            String colorS = args[0].toUpperCase();
            boolean bool= testColor(colorS);
            if(!bool){
                p.sendMessage(Utils.ucs("ilg"));
                return true;
            }
            UUID uuid = p.getUniqueId();
            getColorMap().put(uuid, colorS);
            p.sendMessage(Utils.ucs("ChatColor.done").replaceAll("%color%", ChatColor.valueOf(colorS) + colorS));


        }catch (IllegalArgumentException ex){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }

        return false;
    }
    public boolean testColor(String color){
        if(color.equalsIgnoreCase("magic") || color.equalsIgnoreCase("reset") || color.equalsIgnoreCase("underline") || color.equalsIgnoreCase("STRIKETHROUGH")){
            return false;
        }
        ChatColor.valueOf(color.toUpperCase());
        return true;
    }
    public HashMap<UUID, String> getColorMap(){
        return ChatColorData.chatColors;
    }
}
