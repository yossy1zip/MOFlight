package com.yiorno.moflight;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {

    private final Plugin plugin;
    private FileConfiguration config = null;

    public static Integer checkingInterval;
    public static Integer pricePerMinute;

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
        pricePerMinute = config.getInt("price-per-minute");

    }

}
