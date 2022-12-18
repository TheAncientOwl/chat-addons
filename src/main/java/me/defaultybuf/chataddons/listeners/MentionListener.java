package me.defaultybuf.chataddons.listeners;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.config.Config;

public class MentionListener implements Listener {
  private final Config m_Config;

  public MentionListener(Config config) {
    m_Config = config;
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onChat(AsyncPlayerChatEvent e) {
    final Player player = e.getPlayer();
    if (!player.hasPermission("chataddons.mention"))
      return;

    final String notification = Utils.color(m_Config.getString(Config.MENTION, "format").replace("{name}",
        player.getDisplayName()));
    final Sound sound = Sound.valueOf(m_Config.getString(Config.MENTION, "sound"));
    final int volume = m_Config.getInt(Config.MENTION, "volume");
    final int pitch = m_Config.getInt(Config.MENTION, "pitch");

    HashSet<Player> mentionedPlayers = extractPlayers(e.getMessage());

    for (Player mentionedPlayer : mentionedPlayers) {
      mentionedPlayer.sendMessage(notification);
      mentionedPlayer.playSound(mentionedPlayer.getLocation(), sound, volume, pitch);
    }
  }

  private static HashSet<Player> extractPlayers(String str) {
    HashSet<Player> players = new HashSet<>();
    for (String word : str.split(" ")) {
      Player player = Bukkit.getPlayerExact(word);
      if (player != null && player.hasPermission("chataddons.mention.notify"))
        players.add(player);
    }

    return players;
  }
}
