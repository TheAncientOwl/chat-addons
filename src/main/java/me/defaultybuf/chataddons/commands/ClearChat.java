package me.defaultybuf.chataddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;

public class ClearChat extends BasePluginCommand {

  public ClearChat(Config config, String permission) {
    super(config, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    final int clearChatAmount = m_Config.getInt(Config.CLEAR_CHAT, "amount");

    final String notification = Utils
        .color(m_Config.getString(Config.CLEAR_CHAT, "notify").replace("{name}", sender.getName()));

    for (Player player : Bukkit.getOnlinePlayers()) {
      if (!player.hasPermission("chataddons.clearchat.bypass"))
        clearChat(player, clearChatAmount);

      if (player.hasPermission("chataddons.clearchat.notify"))
        player.sendMessage(notification);
    }

    return true;
  }

  private static void clearChat(Player player, int amount) {
    for (int i = 0; i < amount; i++)
      player.sendMessage(" ");
  }
}
