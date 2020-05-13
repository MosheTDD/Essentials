package me.moshe.training.commands;

import me.moshe.training.Training;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee implements CommandExecutor {
    private Training plugin;

    public Invsee(Training plugin) {
        this.plugin = plugin;
        plugin.getCommand("invsee").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(Utils.ucs("notAPlayer"));
            return true;
        }
        Player p = (Player) sender;
        if(!(p.hasPermission("moshe.invsee"))){
            p.sendMessage(Utils.ucs("noPermission"));
            return true;
        }
        if(args.length != 1){
            p.sendMessage(Utils.ucs("ilg"));
            return true;
        }
        Player t = Bukkit.getPlayerExact(args[0]);
        if(t == null){
            p.sendMessage(Utils.ucs("offlinePlayer").replaceAll("%t%", args[0]));
            return true;
        }
        if(t == p){
            p.sendMessage(Utils.ucs("Invsee.samePlayer"));
            return true;
        }
        p.openInventory(t.getInventory());
        p.sendMessage(Utils.ucs("Invsee.done").replaceAll("%t%", t.getName()));




        return false;
    }
}
