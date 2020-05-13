package me.moshe.training.commands;

import me.moshe.training.Training;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {
    private Training plugin;
    public Speed (Training plugin){
        this.plugin = plugin;
        plugin.getCommand("speed").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players may execute this command.");
            return true;
        }

        Player p = (Player) sender;
        if(p.hasPermission("moshe.speed")){
            if(args.length != 1){
                p.sendMessage("Illegal arguments.");
                return true;
            }
            if(args[0].startsWith("-")){
                p.sendMessage("Can't use negative numbers.");
                return true;
            }
            try{
                float level = Float.parseFloat(args[0]);
                float speed = level / 10;
                if(p.isFlying()){
                    p.setFlySpeed(speed);
                }else {
                    p.setWalkSpeed(speed);
                }
                p.sendMessage("Your speed is set to %speed%".replaceAll("%speed%",String.valueOf(level)));
                return true;
            }catch (IllegalArgumentException e){
                p.sendMessage("Must use a number between 1 and 10.");
                return true;
            }
        }else{
            sender.sendMessage("No permission");
        }






        return false;
    }

}
