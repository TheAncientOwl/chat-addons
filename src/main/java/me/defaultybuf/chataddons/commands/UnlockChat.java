package me.defaultybuf.chataddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;
import me.defaultybuf.chataddons.listeners.LockChatListener;

public class UnlockChat extends BasePluginCommand {

  public UnlockChat(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (!LockChatListener.isLocked()) {
      sender.sendMessage(Utils.color(m_Config.getPrefix() + m_Config.getString(Config.LOCK_CHAT, "already.unlocked")));
      return true;
    }

    LockChatListener.unlock();

    String notification = Utils.color(
        m_Config.getString(Config.LOCK_CHAT, "notify.unlocked").replace("{name}", sender.getName()));

    for (Player player : Bukkit.getOnlinePlayers())
      if (player.hasPermission("chataddons.lockchat.notify"))
        player.sendMessage(notification);

    return true;
  }
}
