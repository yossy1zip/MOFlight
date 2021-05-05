package com.yiorno.moflight;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MOFlight extends JavaPlugin implements Listener {

    private Economy econ;
    private Permission perms;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Config config = new Config(this);
        config.load();

        if (!setupEconomy()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        this.setupPermissions();

        getLogger().info("飛行管理が起動しました");
        getServer().getPluginManager().registerEvents(this, this);

    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public Economy getEconomy() {
        return econ;
    }

    public Permission getPermissions() {
        return perms;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("飛行管理が停止しました");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("flight")){

            Player player = (Player)sender;

            //check number
            if (isInteger(args[0]) == false) {
                player.sendMessage(ChatColor.AQUA + "時間は自然数でお願いします！");
                return true;
            }

            Integer minutes = Integer.valueOf(args[0]);

            if (minutes <= 0) {
                player.sendMessage(ChatColor.AQUA + "時間は自然数でお願いします！");
                return true;
            }

            if (args.length != 0) {
                player.sendMessage(ChatColor.AQUA + "/flight <分> : フライトを購入します");
                player.sendMessage(ChatColor.AQUA + "～ 500 MOFU/分");
                return true;
            }


            if(Variable.flightplayer.contains(player.getPlayer())) {

                player.sendMessage(ChatColor.AQUA + "すでに飛行モードです^～^");
                return true;

            } else {

                ChangeMode changeMode = new ChangeMode();
                changeMode.startFlight(player, minutes);
                return true;

            }
        }

        return false;
    }

    public boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }

}
