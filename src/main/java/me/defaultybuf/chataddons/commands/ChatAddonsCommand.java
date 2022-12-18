package me.defaultybuf.chataddons.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.api.BasePluginCommand;
import me.defaultybuf.chataddons.config.Config;
import me.defaultybuf.chataddons.utils.Utils;

public class ChatAddonsCommand implements CommandExecutor {
  private final HashMap<String, BasePluginCommand> m_Commands;

  private Main m_Plugin;

  public ChatAddonsCommand(Main main) {
    m_Plugin = main;

    m_Commands = new HashMap<>();
    m_Commands.put("reload", new Reload(main, "chataddons.reload"));
    m_Commands.put("help", new Help(main, "chataddons.help"));

    final ClearChat clearChat = new ClearChat(main, "chataddons.help");
    m_Commands.put("clearchat", clearChat);
    m_Commands.put("cc", clearChat);

    final LockChat lockChat = new LockChat(main, "chataddons.lockchat");
    m_Commands.put("lockchat", lockChat);
    m_Commands.put("lc", lockChat);

    final UnlockChat unlockChat = new UnlockChat(main, "chataddons.lockchat");
    m_Commands.put("unlockchat", unlockChat);
    m_Commands.put("uc", unlockChat);

    final Broadcast broadcast = new Broadcast(main, "chataddons.broadcast");
    m_Commands.put("broadcast", broadcast);
    m_Commands.put("bc", broadcast);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("chataddons.use")) {
      sender.sendMessage(Utils
          .color(m_Plugin.getPluginConfig().getPrefix()
              + m_Plugin.getPluginConfig().getString(Config.MESSAGES, "no-permission")));
      return true;
    }

    if (args.length == 0)
      return true;

    BasePluginCommand cmd = m_Commands.get(args[0].toLowerCase());

    if (cmd == null) {
      sender.sendMessage(Utils.color(m_Plugin.getPluginConfig().getString(Config.MESSAGES, "unknown-command")));
      return true;
    }

    return cmd.onCommand(sender, command, label, args);
  }
}
