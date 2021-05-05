package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class Calculation {

    public void calcTime(Player player, Integer minutes) {

        final Integer[] restTime = {minutes};
        Integer maxTime = minutes;


        while (restTime[0] >= 0) {

            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("MOFlight"),
                    new BukkitRunnable() {

                @Override
                public void run() {

                    restTime[0]--;
                    Variable.map.replace(player.getPlayer(), restTime[0]);

                    Interface ui = new Interface();
                    if (restTime[0] == maxTime) {
                        ui.createBossbar(player, restTime[0], maxTime);
                    } else {
                        ui.updateBossbar(player, restTime[0], maxTime);
                    }

                }
            }, 0L, 20L);

        }

        ChangeMode changeMode = new ChangeMode();
        changeMode.endFlight(player);
        return;

    }

}
