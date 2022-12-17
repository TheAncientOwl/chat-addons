package me.defaultybuf.chataddons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.utils.ChatUtils;

public abstract class PluginCommand implements CommandExecutor {
  protected final Config m_Config;
  private final String m_Permission;

  public PluginCommand(Main main, String permission) {
    m_Config = main.getPluginConfig();
    m_Permission = permission;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission(m_Permission) && !(sender instanceof ConsoleCommandSender)) {
      ChatUtils.sendNoPermissionMessage(sender, m_Config);
      return true;
    }

    return execute(sender, args);
  }

  public boolean execute(CommandSender sender, String args[]) {
    throw new UnsupportedOperationException("Unimplemented command");
  }
}
