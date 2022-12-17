package me.defaultybuf.chataddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.utils.Utils;

public class ClearChat extends BasePluginCommand {

  public ClearChat(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    final int clearChatAmount = m_Config.getInt(Config.CLEAR_CHAT, "amount");

    for (Player player : Bukkit.getOnlinePlayers()) {
      if (!player.hasPermission("chataddons.clearchat.bypass"))
        clearChat(player, clearChatAmount);

      if (player.hasPermission("chataddons.clearchat.notify"))
        Utils.sendMessageColor(player,
            m_Config.getString(Config.CLEAR_CHAT, "notify").replace("{name}", sender.getName()));
    }

    return true;
  }

  private static void clearChat(Player player, int amount) {
    for (int i = 0; i < amount; i++)
      player.sendMessage(" ");
  }
}
