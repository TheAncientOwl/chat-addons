package me.defaultybuf.chataddons.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.config.Config;

public class LockChatListener implements Listener {
  private static volatile boolean m_IsChatLocked;
  private final Config m_Config;

  public LockChatListener(Config config) {
    m_Config = config;

    m_IsChatLocked = false;
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onChat(AsyncPlayerChatEvent e) {
    final Player player = e.getPlayer();

    if (m_IsChatLocked && !player.hasPermission("chataddons.lockchat.bypass")) {
      e.setCancelled(true);
      player.sendMessage(Utils.color(m_Config.getString(Config.LOCK_CHAT, "deny")));
    }
  }

  public static void lock() {
    m_IsChatLocked = true;
  }

  public static void unlock() {
    m_IsChatLocked = false;
  }

  public static boolean isLocked() {
    return m_IsChatLocked;
  }
}
