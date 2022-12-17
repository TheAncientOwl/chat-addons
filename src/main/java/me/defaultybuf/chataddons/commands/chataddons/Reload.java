package me.defaultybuf.chataddons.commands.chataddons;

import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.BasePluginCommand;
import me.defaultybuf.chataddons.utils.ChatUtils;

public class Reload extends BasePluginCommand {

  public Reload(Main main, String permsission) {
    super(main, permsission);
  }

  @Override
  public boolean execute(CommandSender sender, String args[]) {
    m_Config.reload();

    ChatUtils.sendMessage(sender, m_Config.getPrefix() + m_Config.getString(Config.MESSAGES, "reload"));

    return true;
  }

}
