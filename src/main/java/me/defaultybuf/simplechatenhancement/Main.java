package me.defaultybuf.simplechatenhancement;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
    private ConfigAccessor m_ConfigAccessor = null;

    @Override
    public void onEnable() {
        m_ConfigAccessor = new ConfigAccessor(this, "config.yml");
        reloadConfig();

        final FileConfiguration config = m_ConfigAccessor.getConfig();

        final boolean ENABLE_CHAT_INJECTOR = config.getBoolean("bracket-placeholders-injector.enabled");
        final boolean ENABLE_CHAT_HOVER = config.getBoolean("chat-hover.enabled");
        final boolean REQUIRE_PAPI_DEPENDENCY = ENABLE_CHAT_INJECTOR || ENABLE_CHAT_HOVER;

        if (REQUIRE_PAPI_DEPENDENCY && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, ChatColor.translateAlternateColorCodes('&',
                    "&4Failed to detect PlaceholderAPI. The plugin will stop now..."));
            return;
        }

        if (ENABLE_CHAT_INJECTOR)
            Bukkit.getServer().getPluginManager().registerEvents(new BracketPlaceholdersInjector(), this);

        if (ENABLE_CHAT_HOVER)
            Bukkit.getServer().getPluginManager().registerEvents(new ChatHover(this), this);

        this.getCommand("sce").setExecutor(new Reload(this));

        Bukkit.getServer().getLogger().log(Level.INFO,
                ChatColor.translateAlternateColorCodes('&', "&aSimpleChatEnhancement enabled!"));
    }

    public void reloadConfig() {
        m_ConfigAccessor.reloadConfig();
    }

    public FileConfiguration getConfig() {
        return m_ConfigAccessor.getConfig();
    }

}
