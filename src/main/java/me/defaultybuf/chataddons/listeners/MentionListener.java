package me.defaultybuf.chataddons.listeners;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.utils.Utils;

public class MentionListener implements Listener {
  private Config m_Config;

  public MentionListener(Config config) {
    m_Config = config;
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    Player player = e.getPlayer();
    if (!player.hasPermission("chataddons.mention"))
      return;

    final String notification = Utils.color(m_Config.getString(Config.MENTION, "format").replace("{name}",
        player.getDisplayName()));
    final Sound sound = Sound.valueOf(m_Config.getString(Config.MENTION, "sound"));
    final int volume = m_Config.getInt(Config.MENTION, "volume");
    final int pitch = m_Config.getInt(Config.MENTION, "pitch");

    HashSet<Player> mentionedPlayers = new HashSet<>();
    for (String word : e.getMessage().split(" ")) {
      Player onlinePlayer = Bukkit.getPlayerExact(word);
      if (onlinePlayer != null && onlinePlayer.hasPermission("chataddons.mention.notify"))
        mentionedPlayers.add(player);
    }

    for (Player mentionedPlayer : mentionedPlayers) {
      mentionedPlayer.sendMessage(notification);
      mentionedPlayer.playSound(mentionedPlayer.getLocation(), sound, volume, pitch);
    }

  }
}
