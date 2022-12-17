package me.defaultybuf.chataddons.commands.chataddons;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.BasePluginCommand;
import me.defaultybuf.chataddons.utils.ChatUtils;

public class Help extends BasePluginCommand {

  public Help(Main main, String permission) {
    super(main, permission);
  }

  @Override
  public boolean execute(CommandSender sender, String args[]) {
    List<String> help = m_Config.getStringList(Config.MESSAGES, "help");
    for (String helpLine : help)
      ChatUtils.sendMessageColor(sender, helpLine);

    return true;
  }
}
