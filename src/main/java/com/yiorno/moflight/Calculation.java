package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import static com.yiorno.moflight.MOFlight.instance;

public class Calculation {

    public void calcTime(Player player, Integer minutes) {

        BukkitTask task;
        Integer restTimeMinutes = minutes;
        final int[] restTimeSeconds = {restTimeMinutes * 60};
        Integer maxTimeSeconds = minutes * 60;

        new BukkitRunnable() {
            @Override
            public void run() {

                if (restTimeSeconds[0]<0) {

                    ChangeMode changeMode = new ChangeMode();
                    changeMode.endFlight(player);

                    cancel();

                } else {

                    Interface ui = new Interface();

                    if (restTimeSeconds[0] == maxTimeSeconds) {
                        ui.createBossbar(player, restTimeSeconds[0], maxTimeSeconds);
                    } else {

                        if (ui.hasBossbar(player) == true) {
                            ui.updateBossbar(player, restTimeSeconds[0], maxTimeSeconds);
                        } else {
                            cancel();
                        }

                    }

                    restTimeSeconds[0]--;
                    Variable.map.replace(player.getPlayer(), restTimeSeconds[0]);

                }
            }
        }.runTaskTimer(instance, 20L, 20L);

        return;

    }

}
