package me.defaultybuf.chataddons.listeners;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.config.Config;

public class StaffChatListener implements Listener {
  private final HashSet<Player> m_Staff;
  private final Config m_Config;

  public StaffChatListener(Config config) {
    m_Config = config;
    m_Staff = new HashSet<>();
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    final Player player = e.getPlayer();

    if (player.hasPermission("chataddons.staffchat"))
      m_Staff.add(player);
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    final Player player = e.getPlayer();

    if (player.hasPermission("chataddons.staffchat") && m_Staff.contains(player))
      m_Staff.remove(player);

  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    final Player player = e.getPlayer();

    final String message = e.getMessage();
    if (!player.hasPermission("chataddons.staffchat")
        || message.length() == 1
        || !message.startsWith(m_Config.getString(Config.STAFF_CHAT, "char")))
      return;

    final String staffMessage = Utils.color(m_Config.getString(Config.STAFF_CHAT, "format")
        .replace("{name}", player.getDisplayName())
        .replace("{message}", message.substring(1, message.length())));

    for (Player staff : m_Staff)
      staff.sendMessage(staffMessage);

    e.setCancelled(true);
  }
}
