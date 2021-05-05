package com.yiorno.moflight;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

//指定時間 mofucraft.member.flight.now 権限を振るプラグイン
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
                return false;
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

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e){
        Player player = e.getPlayer();
        String worldFrom = e.getFrom().getName();
        String worldTo = player.getWorld().getName();

        if (worldFrom == "world"){
            //disable flymode
            player.setFlying(false);
            player.sendMessage(ChatColor.AQUA + "建築ワールド外に出たので飛行モードが無効になりました！");
            player.sendMessage(ChatColor.AQUA + "飛べなくても時間は消費されるのでご注意ください");
        }

        if (worldTo == "world" ){
            //re-enable flymode
            player.setFlying(true);
            player.sendMessage(ChatColor.AQUA + "建築ワールドに戻ったので飛行モードが再度有効になりました！");
        }
    }

//    @EventHandler
//    public void onQuit(PlayerQuitEvent e) {
//       Player player = e.getPlayer();
//
//        if (Variable.flightplayer.contains(player.getPlayer())) {
//            Variable.flightplayer.remove(player.getPlayer());
//            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//            String command = "lp user " + player.getName() + " parent remove flight";
//            Bukkit.dispatchCommand(console, command);
//        }
//    }

//    @EventHandler
//    public void onJoin(PlayerJoinEvent e) {
//        Player player = e.getPlayer();

//        if (Variable.flightplayer.contains(player.getPlayer())) {
//            Variable.flightplayer.remove(player.getPlayer());
//            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//            String command = "lp user " + player.getName() + " parent remove flight";
//            Bukkit.dispatchCommand(console, command);
//        }
//    }

}
