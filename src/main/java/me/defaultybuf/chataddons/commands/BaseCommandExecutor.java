package me.defaultybuf.chataddons.commands;

import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.utils.ChatUtils;

public abstract class BaseCommandExecutor {
  protected final Config m_Config;
  private final String m_Permission;

  public BaseCommandExecutor(Main main, String permission) {
    m_Config = main.getPluginConfig();
    m_Permission = permission;
  }

  protected boolean hasPermission(CommandSender sender) {
    if (!sender.hasPermission(m_Permission)) {
      ChatUtils.sendNoPermissionMessage(sender, m_Config);
      return false;
    }
    return true;
  }

  public boolean execute(CommandSender sender, String args[]) {
    throw new UnsupportedOperationException("Unimplemented command");
  }
}
