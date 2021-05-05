package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Interface {

    public void createBossbar(Player player, Integer restTimeSeconds, Integer maxTimeSeconds){

        double percentage = restTimeSeconds * 100 / maxTimeSeconds;
        Integer maxTimeMinutes = maxTimeSeconds / 60;

        //create bossbar
        BossBar bossbar =  Bukkit.getServer().createBossBar(ChatColor.AQUA + "" + maxTimeMinutes + "分のフライト^^", BarColor.BLUE, BarStyle.SOLID);
        bossbar.setVisible(true);
        bossbar.setTitle(ChatColor.AQUA + "フライト : 残り" + restTimeSeconds + "秒");
        bossbar.setProgress(percentage);
        bossbar.addPlayer(player);

        Variable.bossBars.put(player, bossbar);
    }

    public void updateBossbar(Player player, Integer restTimeSeconds, Integer maxTimeSeconds){

        double percentage = restTimeSeconds * 100 / maxTimeSeconds;
        Integer maxTimeMinutes = maxTimeSeconds / 60;

        //update bossbarupdate
        BossBar bossbar = Variable.bossBars.get(player);
        bossbar.setTitle(ChatColor.AQUA + "フライト : 残り" + restTimeSeconds + "秒");
        bossbar.setProgress(percentage);

    }


    public void removeBossbar(Player player){

        //remove bossbar
        BossBar bossbar =  Variable.bossBars.get(player);
        Variable.bossBars.remove(player);
        bossbar.removePlayer(player);
    }

}
