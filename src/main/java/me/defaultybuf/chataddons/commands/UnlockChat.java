package me.defaultybuf.chataddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.listeners.LockChatListener;
import me.defaultybuf.chataddons.utils.Utils;

public class UnlockChat extends BasePluginCommand {

  public UnlockChat(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (!LockChatListener.isLocked()) {
      Utils.sendMessageColor(sender,
          m_Config.getPrefix() + m_Config.getString(Config.LOCK_CHAT, "already.unlocked"));
      return true;
    }

    LockChatListener.unlock();

    String unlockedNotify = m_Config.getString(Config.LOCK_CHAT, "notify.unlocked").replace("{name}", sender.getName());
    for (Player player : Bukkit.getOnlinePlayers())
      if (player.hasPermission("chataddons.lockchat.notify"))
        Utils.sendMessageColor(player, unlockedNotify);

    return true;
  }
}
