package me.defaultybuf.chataddons.commands.chataddons;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.BasePluginCommand;
import me.defaultybuf.chataddons.listeners.LockChatListener;
import me.defaultybuf.chataddons.utils.ChatUtils;

public class UnlockChat extends BasePluginCommand {

  public UnlockChat(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (!LockChatListener.isLocked()) {
      ChatUtils.sendMessage(sender, m_Config.getPrefix() + m_Config.getString(Config.LOCK_CHAT, "already.unlocked"));
      return true;
    }

    LockChatListener.unlock();

    String unlockedNotify = m_Config.getString(Config.LOCK_CHAT, "notify.unlocked").replace("{name}", sender.getName());
    for (Player player : Bukkit.getOnlinePlayers())
      if (player.hasPermission("chataddons.lockchat.notify"))
        ChatUtils.sendMessage(player, unlockedNotify);

    return true;
  }
}
