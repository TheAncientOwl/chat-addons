package me.defaultybuf.simplechatenhancement;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
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
    Player player = e.getPlayer();

    // create message component
    final TextComponent messageText = new TextComponent(
        TextComponent.fromLegacyText(e.getFormat().replace("%2$s", e.getMessage())));

    // create hover event
    List<String> hoverLines = m_Plugin.getConfig().getStringList("chat-hover.format");
    for (int i = 0; i < hoverLines.size(); i++)
      hoverLines.set(i, ChatUtils.setPlaceholdersColor(player, hoverLines.get(i)));
    final HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
        new Text(TextComponent.fromLegacyText(String.join("\n", hoverLines))));

    // create click event
    final String clickAction = m_Plugin.getConfig().getString("chat-hover.click-action");
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
