package me.defaultybuf.simplechatenhancement;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, ChatColor.translateAlternateColorCodes('&',
                    "&4Failed to detect PlaceholderAPI. The plugin will stop now..."));
            return;
        }

        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);

        Bukkit.getServer().getLogger().log(Level.INFO,
                ChatColor.translateAlternateColorCodes('&', "&aSimpleChatEnhancement enabled!"));
    }

}
