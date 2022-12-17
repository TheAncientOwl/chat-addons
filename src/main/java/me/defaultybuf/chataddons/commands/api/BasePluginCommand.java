package me.defaultybuf.chataddons.commands.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.utils.Utils;

public abstract class BasePluginCommand implements CommandExecutor, IPluginCommand {
  protected final Config m_Config;
  private final String m_Permission;

  public BasePluginCommand(Main main, String permission) {
    m_Config = main.getPluginConfig();
    m_Permission = permission;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission(m_Permission)) {
      Utils.sendNoPermissionMessage(sender, m_Config);
      return true;
    }

    return execute(sender, args);
  }
}
