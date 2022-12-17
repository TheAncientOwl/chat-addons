package me.defaultybuf.chataddons.listeners;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.utils.ChatUtils;

public class StaffChat implements Listener {
  private HashSet<Player> m_Staff;
  private Config m_Config;

  public StaffChat(Config config) {
    m_Config = config;
    m_Staff = new HashSet<>();
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();

    if (!player.hasPermission("chataddons.staffchat"))
      return;

    m_Staff.add(player);
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    Player player = e.getPlayer();

    if (!player.hasPermission("chataddons.staffchat"))
      return;

    m_Staff.remove(player);
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    if (e.isCancelled())
      return;

    final Player player = e.getPlayer();

    final String message = e.getMessage();
    if (!player.hasPermission("chataddons.staffchat")
        || message.length() == 1
        || !message.startsWith(m_Config.getString(Config.STAFF_CHAT, "char")))
      return;

    final String staffMessage = ChatUtils.colorize(m_Config.getString(Config.STAFF_CHAT, "format")
        .replace("{name}", player.getDisplayName())
        .replace("{message}", message.substring(1, message.length())));

    for (Player staff : m_Staff)
      staff.sendMessage(staffMessage);

    e.setCancelled(true);
  }
}
