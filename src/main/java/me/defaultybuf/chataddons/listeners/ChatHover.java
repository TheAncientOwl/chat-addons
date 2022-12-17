package me.defaultybuf.chataddons.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.utils.ChatUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ChatHover implements Listener {
  private final Main m_Plugin;

  public ChatHover(Main plugin) {
    m_Plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onChat(AsyncPlayerChatEvent e) {
    if (e.isCancelled())
      return;

    Player player = e.getPlayer();

    // create message component
    final TextComponent messageText = new TextComponent(
        TextComponent.fromLegacyText(e.getFormat().replace("%2$s", e.getMessage())));

    // create hover event
    List<String> hoverLines = m_Plugin.getPluginConfig().getStringList(Config.CHAT_HOVER, "format");
    for (int i = 0; i < hoverLines.size(); i++)
      hoverLines.set(i, ChatUtils.setPlaceholdersColor(player, hoverLines.get(i)));
    final HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
        new Text(TextComponent.fromLegacyText(String.join("\n", hoverLines))));

    // create click event
    final String clickAction = m_Plugin.getPluginConfig().getString(Config.CHAT_HOVER, "click-action");
    final ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
        PlaceholderAPI.setPlaceholders(player, clickAction.substring(1, clickAction.length() - 1)));

    // build message component
    final BaseComponent[] messageComponent = new ComponentBuilder()
        .event(hoverEvent)
        .event(clickEvent)
        .append(messageText)
        .create();

    // send to recipients & clear
    for (Player recipientPlayer : e.getRecipients()) {
      recipientPlayer.spigot().sendMessage(messageComponent);
    }

    e.getRecipients().clear();
  }
}
