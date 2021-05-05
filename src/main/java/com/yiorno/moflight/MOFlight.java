package com.yiorno.moflight;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class MOFlight extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Config config = new Config(this);
        config.load();

        getLogger().info("飛行管理が起動しました");
        getServer().getPluginManager().registerEvents(this, this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                Automation automation = new Automation();
                automation.calcTime();
            }

        }, 0L, 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("flight")){

            Player player = (Player)sender;
            //User user = (User)sender;

            if(val.flightplayer.contains(player.getPlayer())) {

                player.sendMessage(ChatColor.YELLOW + "すでに飛行モードです^～^");
                return true;

            } else {

                if (args.length != 0) {

                    Integer time = Integer.valueOf(args[0]);

                    ChangeMode changeMode = new ChangeMode();
                    changeMode.startFlight(player, time);
                    return true;

                } else {
                    return true;
                }

            }
        }

        return false;
    }

}
