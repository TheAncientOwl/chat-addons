package me.defaultybuf.chataddons;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.defaultybuf.chataddons.commands.ChatAddonsCommand;
import me.defaultybuf.chataddons.config.Config;
import me.defaultybuf.chataddons.listeners.BracketPlaceholdersInjector;
import me.defaultybuf.chataddons.listeners.CapsExtensionListener;
import me.defaultybuf.chataddons.listeners.ChatHoverListener;
import me.defaultybuf.chataddons.listeners.LockChatListener;
import me.defaultybuf.chataddons.listeners.MentionListener;
import me.defaultybuf.chataddons.listeners.StaffChatListener;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
    private Config m_Config = null;

    @Override
    public void onEnable() {
        m_Config = new Config(this);

        registerListeners();
        registerCommands();
        announceEnabled();
    }

    public void registerListeners() {
        final PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        if (m_Config.dependsOnPAPI() && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, ChatColor.translateAlternateColorCodes('&',
                    "&4Failed to detect PlaceholderAPI. The plugin will stop now..."));
        } else {
            if (m_Config.getBoolean(Config.PLACEHOLDERS_INJECTOR, "enabled"))
                pluginManager.registerEvents(new BracketPlaceholdersInjector(), this);

            if (m_Config.getBoolean(Config.CHAT_HOVER, "enabled"))
                pluginManager.registerEvents(new ChatHoverListener(m_Config), this);
        }

        if (m_Config.getBoolean(Config.CLEAR_CHAT, "enabled"))
            pluginManager.registerEvents(new LockChatListener(m_Config), this);

        if (m_Config.getBoolean(Config.STAFF_CHAT, "enabled"))
            pluginManager.registerEvents(new StaffChatListener(m_Config), this);

        if (m_Config.getBoolean(Config.MENTION, "enabled"))
            pluginManager.registerEvents(new MentionListener(m_Config), this);

        if (m_Config.getBoolean(Config.CAPS_EXTENSION, "enabled"))
            pluginManager.registerEvents(new CapsExtensionListener(m_Config), this);
    }

    public void registerCommands() {
        this.getCommand("chataddons").setExecutor(new ChatAddonsCommand(m_Config));
    }

    public void announceEnabled() {
        Bukkit.getServer().getLogger().log(Level.INFO, "ChatAddons enabled!");
    }
}
