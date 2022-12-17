package me.defaultybuf.chataddons.commands.chataddons;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.BasePluginCommand;
import me.defaultybuf.chataddons.listeners.LockChatListener;
import me.defaultybuf.chataddons.utils.Utils;

public class LockChat extends BasePluginCommand {

  public LockChat(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (LockChatListener.isLocked()) {
      Utils.sendMessageColor(sender, m_Config.getPrefix() + m_Config.getString(Config.LOCK_CHAT, "already.locked"));
      return true;
    }

    LockChatListener.lock();

    final String notify = m_Config.getString(Config.LOCK_CHAT, "notify.locked").replace("{name}", sender.getName());
    for (Player player : Bukkit.getOnlinePlayers())
      if (player.hasPermission("chataddons.lockchat.notify"))
        Utils.sendMessageColor(player, notify);

    return true;
  }
}
