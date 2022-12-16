package me.defaultybuf.simplechatenhancement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
  private static final Pattern RGB_PATTERN = Pattern.compile("&#[a-fA-F0-9]{6}");

  public static String colorize(String str) {
    return ChatColor.translateAlternateColorCodes('&', colorizeRGB(str));
  }

  private static String colorizeRGB(String str) {
    Matcher matcher = RGB_PATTERN.matcher(str);
    while (matcher.find()) {
      String color = str.substring(matcher.start() + 1, matcher.end());
      str = str.substring(0, matcher.start()) + ChatColor.of(color) +
          str.substring(matcher.end());
      matcher = RGB_PATTERN.matcher(str);
    }

    return str;
  }

  public static String setBracketPlaceholdersColor(Player player, String str) {
    return colorize(PlaceholderAPI.setBracketPlaceholders(player, str));
  }

  public static String setPlaceholdersColor(Player player, String str) {
    return colorize(PlaceholderAPI.setPlaceholders(player, str));
  }
}
