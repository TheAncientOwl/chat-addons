package me.defaultybuf.chataddons.commands.api;

import org.bukkit.command.CommandSender;

public interface IPluginCommand {
  public boolean execute(CommandSender sender, String[] args);
}
