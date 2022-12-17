package me.defaultybuf.chataddons.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.utils.Utils;

public class CapsExtensionListener implements Listener {
  private Config m_Config;

  public CapsExtensionListener(Config config) {
    m_Config = config;
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    if (e.getPlayer().hasPermission("chataddons.caps-extension.bypass"))
      return;

    final int extensionLimit = m_Config.getInt(Config.CAPS_EXTENSION, "extension-letters");
    final int capsLimit = m_Config.getInt(Config.CAPS_EXTENSION, "caps-letters");

    StringBuilder newMessage = new StringBuilder();
    for (String word : e.getMessage().split(" ")) {
      if (Utils.isPlayer(word)) {
        newMessage.append(word).append(' ');
        continue;
      }

      StringBuilder newWordBuilder = new StringBuilder();

      char[] sNormal = word.toCharArray();
      char[] sLower = word.toLowerCase().toCharArray();
      int sz = sNormal.length;
      int nrCaps = 0;
      char lastChar = '.';
      int nra = 0;
      for (int i = 0; i < sz; ++i) {
        if ('A' <= sNormal[i] && sNormal[i] <= 'Z')
          ++nrCaps;

        if (sLower[i] != lastChar) {
          nra = 1;
          lastChar = sLower[i];
        } else if (sLower[i] == lastChar) {
          ++nra;
        }

        if (nra < extensionLimit)
          newWordBuilder.append(sNormal[i]);
      }

      String newWord = newWordBuilder.toString();

      newMessage.append(nrCaps >= capsLimit ? newWord.toLowerCase() : newWord).append(' ');
    }

    e.setMessage(newMessage.toString());
  }
}
