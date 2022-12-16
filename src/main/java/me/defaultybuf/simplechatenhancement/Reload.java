package me.defaultybuf.simplechatenhancement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class Reload implements CommandExecutor {

  private Main main;

  public Reload(Main instance) {
    main = instance;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!cmd.getName().equalsIgnoreCase("sce"))
      return true;

    if (!sender.hasPermission("sce.reload")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have the permission to do this!"));
      return true;
    }

    main.reloadConfig();
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5SimpleChatEnhancement &8> &areloaded&8!"));
    return true;
  }

}