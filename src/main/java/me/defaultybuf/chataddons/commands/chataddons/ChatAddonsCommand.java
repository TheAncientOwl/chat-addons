package me.defaultybuf.chataddons.commands.chataddons;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.defaultybuf.chataddons.Config;
import me.defaultybuf.chataddons.Main;
import me.defaultybuf.chataddons.commands.BasePluginCommand;
import me.defaultybuf.chataddons.utils.ChatUtils;

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
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("chataddons.use")) {
      ChatUtils.sendNoPermissionMessage(sender, m_Plugin.getPluginConfig());
      return true;
    }

    if (args.length == 0)
      return true;

    BasePluginCommand cmd = m_Commands.get(args[0].toLowerCase());

    if (cmd == null) {
      ChatUtils.sendMessage(sender, m_Plugin.getPluginConfig().getString(Config.MESSAGES, "unknown-command"));
      return true;
    }

    return cmd.onCommand(sender, command, label, args);
  }
}
