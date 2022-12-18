package me.defaultybuf.chataddons.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Utils;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;

public class ChatAddonsCommand implements CommandExecutor {
  private final HashMap<String, BasePluginCommand> m_Commands;

  private final Config m_Config;

  public ChatAddonsCommand(Config config) {
    m_Config = config;

    m_Commands = new HashMap<>();
    m_Commands.put("reload", new Reload(config, "chataddons.reload"));
    m_Commands.put("help", new Help(config, "chataddons.help"));

    final ClearChat clearChat = new ClearChat(config, "chataddons.help");
    m_Commands.put("clearchat", clearChat);
    m_Commands.put("cc", clearChat);

    final LockChat lockChat = new LockChat(config, "chataddons.lockchat");
    m_Commands.put("lockchat", lockChat);
    m_Commands.put("lc", lockChat);

    final UnlockChat unlockChat = new UnlockChat(config, "chataddons.lockchat");
    m_Commands.put("unlockchat", unlockChat);
    m_Commands.put("uc", unlockChat);

    final Broadcast broadcast = new Broadcast(config, "chataddons.broadcast");
    m_Commands.put("broadcast", broadcast);
    m_Commands.put("bc", broadcast);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("chataddons.use")) {
      sender.sendMessage(Utils.color(m_Config.getPrefix() + m_Config.getString(Config.MESSAGES, "no-permission")));
      return true;
    }

    if (args.length == 0)
      return true;

    final BasePluginCommand cmd = m_Commands.get(args[0].toLowerCase());

    if (cmd == null) {
      sender.sendMessage(Utils.color(m_Config.getString(Config.MESSAGES, "unknown-command")));
      return true;
    }

    return cmd.onCommand(sender, command, label, args);
  }
}
