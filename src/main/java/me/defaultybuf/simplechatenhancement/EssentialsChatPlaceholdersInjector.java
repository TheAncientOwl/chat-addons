package me.defaultybuf.simplechatenhancement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;

public class EssentialsChatPlaceholdersInjector implements Listener {
  private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onChat(AsyncPlayerChatEvent e) {
    Player player = e.getPlayer();
    String message = e.getMessage();
    String format = e.getFormat();

    if (PlaceholderAPI.containsBracketPlaceholders(message)) {
      message = PlaceholderAPI.setBracketPlaceholders(player, message);
      e.setMessage(colorize(message));
    }

    if (PlaceholderAPI.containsBracketPlaceholders(format)) {
      format = PlaceholderAPI.setBracketPlaceholders(player, format);
      e.setFormat(colorize(format));
    }
  }

  public static String colorize(String str) {
    Matcher matcher = pattern.matcher(str);
    while (matcher.find()) {
      String color = str.substring(matcher.start() + 1, matcher.end());
      str = str.substring(0, matcher.start()) + ChatColor.of(color) +
          str.substring(matcher.end());
      matcher = pattern.matcher(str);
    }

    return ChatColor.translateAlternateColorCodes('&', str);
  }
}
