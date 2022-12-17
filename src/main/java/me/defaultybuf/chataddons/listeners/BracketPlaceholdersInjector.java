package me.defaultybuf.chataddons.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.defaultybuf.chataddons.utils.ChatUtils;

public class BracketPlaceholdersInjector implements Listener {

  @EventHandler(priority = EventPriority.HIGH)
  public void onChat(AsyncPlayerChatEvent e) {
    if (e.isCancelled())
      return;

    final Player player = e.getPlayer();
    final String message = e.getMessage();
    final String format = e.getFormat();

    if (PlaceholderAPI.containsBracketPlaceholders(message)) {
      e.setMessage(ChatUtils.setBracketPlaceholdersColor(player, message));
    }

    if (PlaceholderAPI.containsBracketPlaceholders(format)) {
      e.setFormat(ChatUtils.setBracketPlaceholdersColor(player, format));
    }
  }
}
