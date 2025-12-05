package com.example.daytab;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayTabCommand implements CommandExecutor {
    private final DayTabPlugin plugin;

    public DayTabCommand(DayTabPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "DayTab is running. Use /daytab reload to reload config (no config yet)."
            );
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            // placeholder for future config
            sender.sendMessage(ChatColor.YELLOW + "DayTab: reload not implemented (no config).");
            return true;
        }

        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(ChatColor.RED + "Unknown subcommand. Use /daytab reload");
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown subcommand.");
        }
        return true;
    }
}
