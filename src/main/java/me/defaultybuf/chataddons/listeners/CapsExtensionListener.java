package me.defaultybuf.chataddons.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.defaultybuf.chataddons.config.Config;

public class CapsExtensionListener implements Listener {
  private final Config m_Config;

  public CapsExtensionListener(Config config) {
    m_Config = config;
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    if (e.getPlayer().hasPermission("chataddons.caps-extension.bypass"))
      return;

    final int extensionLimit = m_Config.getInt(Config.CAPS_EXTENSION, "extension-letters");
    final int capsLimit = m_Config.getInt(Config.CAPS_EXTENSION, "caps-letters");

    final StringBuilder newMessageBuilder = new StringBuilder();

    final String[] words = e.getMessage().split(" ");
    for (String word : words) {
      if (Bukkit.getPlayerExact(word) != null) {
        newMessageBuilder.append(word).append(' ');
        continue;
      }

      final StringBuilder newWordBuilder = new StringBuilder();

      int capsCount = 0;
      int seenCount = 0;
      char lastChar = '.';
      for (int i = 0, length = word.length(); i < length; ++i) {
        final char currentChar = word.charAt(i);
        final char currentCharLower = Character.toLowerCase(currentChar);

        // count caps characters
        if (Character.isUpperCase(currentChar))
          ++capsCount;

        // extension
        if (currentCharLower != lastChar) {
          seenCount = 1;
          lastChar = currentCharLower;
        } else if (currentCharLower == lastChar) {
          ++seenCount;
        }

        if (seenCount <= extensionLimit)
          newWordBuilder.append(currentChar);
      }

      final String newWord = newWordBuilder.toString();

      newMessageBuilder.append(capsCount >= capsLimit ? newWord.toLowerCase() : newWord).append(' ');
    }

    e.setMessage(newMessageBuilder.toString());
  }
}
