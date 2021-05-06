package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Interface {

    public void createBossbar(Player player, Integer restTimeSeconds, Integer maxTimeSeconds){

        double progress = restTimeSeconds / maxTimeSeconds;
        Integer maxTimeMinutes = maxTimeSeconds / 60;

        //create bossbar
        BossBar bossbar =  Bukkit.getServer().createBossBar(ChatColor.AQUA + "" + maxTimeMinutes + "分のフライト^^", BarColor.BLUE, BarStyle.SOLID);
        bossbar.setVisible(true);
        bossbar.setTitle(ChatColor.AQUA + "フライト : 残り" + restTimeSeconds + "秒");
        bossbar.setProgress(progress);
        bossbar.addPlayer(player);

        Variable.bossBars.put(player, bossbar);

        return;
    }

    public void updateBossbar(Player player, Integer restTimeSeconds, Integer maxTimeSeconds){

        double progress = restTimeSeconds / maxTimeSeconds;

        //update bossbarupdate
        BossBar bossbar = Variable.bossBars.get(player);

        if (bossbar != null){

            bossbar.setTitle(ChatColor.AQUA + "フライト : 残り" + restTimeSeconds + "秒");
            bossbar.setProgress(progress);


        } else {

            createBossbar(player, restTimeSeconds, maxTimeSeconds);

        }


        return;

    }


    public void removeBossbar(Player player){

        //remove bossbar
        BossBar bossbar =  Variable.bossBars.get(player);
        Variable.bossBars.remove(player);
        bossbar.removePlayer(player);

        return;
    }


    public boolean hasBossbar(Player player){

        //check bossbar
        BossBar bossbar =  Variable.bossBars.get(player);
        if (bossbar != null){
            return true;
        } else {
            return false;
        }

    }

}
