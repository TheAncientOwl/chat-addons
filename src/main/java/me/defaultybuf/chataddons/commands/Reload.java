package me.defaultybuf.chataddons.commands;

import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.utils.Utils;

public class Reload extends BasePluginCommand {

  public Reload(Main main, String permsission) {
    super(main, permsission);
  }

  @Override
  public boolean execute(CommandSender sender, String args[]) {
    m_Config.reload();

    sender.sendMessage(Utils.color(m_Config.getPrefix() + m_Config.getString(Config.MESSAGES, "reload")));

    return true;
  }

}
