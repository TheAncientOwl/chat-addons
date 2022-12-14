package me.defaultybuf.chataddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;

public class Broadcast extends BasePluginCommand {

  public Broadcast(Config config, String permission) {
    super(config, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (args.length < 2)
      return true;

    final StringBuilder sb = new StringBuilder();
    for (int i = 1; i < args.length; i++)
      sb.append(args[i]).append(' ');

    final String message = Utils
        .color(m_Config.getString(Config.BROADCAST, "format").replace("{message}", sb.toString()));

    Bukkit.broadcast(message, "chataddons.broadcast.receive");

    return true;
  }
}
