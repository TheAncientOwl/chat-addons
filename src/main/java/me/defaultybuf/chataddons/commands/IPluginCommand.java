package me.defaultybuf.chataddons.commands;

import org.bukkit.command.CommandSender;

public interface IPluginCommand {
  public boolean execute(CommandSender sender, String[] args);
}
