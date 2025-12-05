package com.example.daytab;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class DayTabPlugin extends JavaPlugin {
    private BukkitTask task;

    @Override
    public void onEnable() {
        getLogger().info("DayTab plugin enabling...");

        // Schedule repeating task every 20 ticks (1 second)
        // Use scoreboard team prefixes to show day count in the tab list (works without NMS)
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) {
            getLogger().warning("Could not get ScoreboardManager; DayTab will not run.");
            return;
        }

        Scoreboard scoreboard = manager.getMainScoreboard();

        task = Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                long fullTime = p.getWorld().getFullTime();
                long dayCount = fullTime / 24000L + 1; // start days at 1
                String prefix = ChatColor.YELLOW + "Day: " + ChatColor.AQUA + dayCount + ChatColor.RESET + " ";

                // team names are limited in length; create a short unique id per player
                String teamName = "dt_" + p.getUniqueId().toString().replace("-", "").substring(0, 12);
                Team team = scoreboard.getTeam(teamName);
                if (team == null) {
                    team = scoreboard.registerNewTeam(teamName);
                    // ensure the entries are tracked per player
                }
                // update prefix if needed
                if (!prefix.equals(team.getPrefix())) {
                    team.setPrefix(prefix);
                }
                if (!team.hasEntry(p.getName())) {
                    team.addEntry(p.getName());
                }
            }
        }, 0L, 20L);

        // Register command
        this.getCommand("daytab").setExecutor(new DayTabCommand(this));

        getLogger().info("DayTab plugin enabled.");
    }

    @Override
    public void onDisable() {
        if (task != null) task.cancel();
        getLogger().info("DayTab plugin disabled.");
    }
}
