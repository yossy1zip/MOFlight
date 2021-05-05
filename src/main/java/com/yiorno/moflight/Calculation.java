package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class Calculation {

    public void calcTime(Player player, Integer minutes) {

        Integer restTimeMinutes = minutes;
        final int[] restTimeSeconds = {restTimeMinutes * 60};
        Integer maxTimeSeconds = minutes * 60;


        while (restTimeSeconds[0] >= 0) {

            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("MOFlight"),
                    new BukkitRunnable() {

                @Override
                public void run() {

                    restTimeSeconds[0]--;
                    Variable.map.replace(player.getPlayer(), restTimeSeconds[0]);

                    Interface ui = new Interface();

                    if (restTimeSeconds[0] == maxTimeSeconds) {
                        ui.createBossbar(player, restTimeSeconds[0], maxTimeSeconds);
                    } else {
                        ui.updateBossbar(player, restTimeSeconds[0], maxTimeSeconds);
                    }

                }
            }, 0L, 20L);

        }

        ChangeMode changeMode = new ChangeMode();
        changeMode.endFlight(player);
        Interface ui = new Interface();
        ui.removeBossbar(player);

        return;

    }

}
