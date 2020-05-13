package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Love implements CommandExecutor {
    private Training plugin;
    public static ArrayList<UUID> loveList = new ArrayList<>();
    public Love(Training plugin){
        this.plugin = plugin;
        plugin.getCommand("love").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players may execute this command.");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("moshe.love")){
            p.sendMessage("You don't have permission to execute this command.");
            return true;
        }
        if(args.length != 0){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        if(loveList.contains(p.getUniqueId())){
            loveList.remove(p.getUniqueId());
            p.sendMessage("You have disabled love mode.");
            return true;
        }
        loveList.add(p.getUniqueId());
        p.sendMessage("You have activated love mode.");
        return false;
    }
}
