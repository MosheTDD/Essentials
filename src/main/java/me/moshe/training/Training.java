package me.moshe.training;

import me.moshe.training.commands.*;
import me.moshe.training.file.ChatColorData;
import me.moshe.training.listeners.*;
import me.moshe.training.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Training extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        Bukkit.getConsoleSender().sendMessage(Utils.color("&6Training plugin is online."));
        ChatColorData.setup();
        new Utils(this);
        new Speed(this);
        new Fly(this);
        new Getpos(this);
        new Love(this);
        new Freezechat(this);
        new Freezeplayer(this);
        new Setspawn(this);
        new Spawn(this);
        new Cage(this);
        new RemoveBlock(this);
        new MsgColor(this);
        new ChangeChatColor(this);
        new Invsee(this);

        getServer().getPluginManager().registerEvents(new LoveEvents(this), this);
        getServer().getPluginManager().registerEvents(new FreezechatEvents(this), this);
        getServer().getPluginManager().registerEvents(new FreezeEvents(this), this);
        getServer().getPluginManager().registerEvents(new CageEvents(this), this);
        getServer().getPluginManager().registerEvents(new ChangeChatColorEvents(this), this);
    }

    @Override
    public void onDisable() {
        ChatColorData.save();
        // Plugin shutdown logic
    }
}
