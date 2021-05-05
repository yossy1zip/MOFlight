package com.yiorno.moflight;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {

    private final Plugin plugin;
    private FileConfiguration config = null;

    public static Integer checkingInterval;
    public static Integer price1;
    public static Integer price2;
    public static Integer price3;

    public Config(Plugin plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        plugin.saveDefaultConfig();
        if (config != null) {
            plugin.reloadConfig();
        }
        config = plugin.getConfig();

        checkingInterval = config.getInt("checking-interval");
        price1 = config.getInt("price1");
        price2 = config.getInt("price2");
        price3 = config.getInt("price3");
        //prices per minute

    }

}
