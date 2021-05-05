package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Interface {

    public void createBossbar(Player player, Integer restTime, Integer maxTime){

        double percentage = restTime * 100 / maxTime;

        //create bossbar
        BossBar bossbar =  Bukkit.getServer().createBossBar(ChatColor.AQUA + "フライト" + restTime + "分", BarColor.BLUE, BarStyle.SOLID);
        bossbar.setVisible(true);
        bossbar.setProgress(percentage);
        bossbar.addPlayer(player);
    }

    public void updateBossbar(Player player, Integer restTime, Integer maxTime){

        double percentage = restTime * 100 / maxTime;

        //create bossbar
        BossBar bossbar =  Bukkit.getServer().createBossBar(ChatColor.AQUA + "フライト" + restTime + "分", BarColor.BLUE, BarStyle.SOLID);
        bossbar.setVisible(true);
        bossbar.setProgress(percentage);
        bossbar.addPlayer(player);
    }


    public void removeBossbar(Player player){

        //remove bossbar
        BossBar bossbar =  Bukkit.getServer().createBossBar(ChatColor.AQUA + "フライト" + restTime + "分", BarColor.BLUE, BarStyle.SOLID);
        bossbar.setVisible(true);
        bossbar.setProgress(percentage);
        bossbar.addPlayer(player);
    }

}
