package me.defaultybuf.chataddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;
import me.defaultybuf.chataddons.listeners.LockChatListener;
import me.defaultybuf.chataddons.utils.Utils;

public class LockChat extends BasePluginCommand {

  public LockChat(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (LockChatListener.isLocked()) {
      sender.sendMessage(Utils.color(m_Config.getPrefix() + m_Config.getString(Config.LOCK_CHAT, "already.locked")));
      return true;
    }

    LockChatListener.lock();

    final String notification = Utils
        .color(m_Config.getString(Config.LOCK_CHAT, "notify.locked").replace("{name}", sender.getName()));

    for (Player player : Bukkit.getOnlinePlayers())
      if (player.hasPermission("chataddons.lockchat.notify"))
        player.sendMessage(notification);

    return true;
  }
}
