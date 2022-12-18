package me.defaultybuf.chataddons.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;

public class Help extends BasePluginCommand {

  public Help(Config config, String permission) {
    super(config, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String args[]) {
    final List<String> help = m_Config.getStringList(Config.MESSAGES, "help");
    for (String helpLine : help)
      sender.sendMessage(Utils.color(helpLine));

    return true;
  }
}
