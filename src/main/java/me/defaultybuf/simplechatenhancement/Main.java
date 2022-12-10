package me.defaultybuf.simplechatenhancement;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getLogger().log(Level.INFO, "\n\n\nHELLO THERE!");
    }
}
