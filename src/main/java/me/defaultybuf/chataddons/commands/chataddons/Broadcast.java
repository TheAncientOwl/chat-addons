package me.defaultybuf.chataddons.commands.chataddons;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.BasePluginCommand;
import me.defaultybuf.chataddons.utils.ChatUtils;

public class Broadcast extends BasePluginCommand {

  public Broadcast(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String[] args) {
    if (args.length < 2)
      return true;

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < args.length; i++)
      sb.append(args[i]).append(' ');

    final String message = ChatUtils
        .colorize(m_Config.getString(Config.BROADCAST, "format").replace("{message}", sb.toString()));

    Bukkit.broadcast(message, "chataddons.broadcast.receive");

    return true;
  }
}