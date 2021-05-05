package com.yiorno.moflight;

import org.bukkit.entity.Player;

public class Calculation {

    public void calcTime(Player player, Integer minutes) {

        Integer restTime = minutes;

        while (restTime >= 0) {
            restTime =- restTime;
            Variable.map.replace(player.getPlayer(), restTime);
        }

        ChangeMode changeMode = new ChangeMode();
        changeMode.endFlight(player);
        return;

    }

}
