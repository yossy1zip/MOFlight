package com.yiorno.moflight;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static com.yiorno.moflight.Config.*;
import static org.bukkit.Bukkit.getLogger;

public class ChangeMode {

    public void checkChange(Player player, Integer minutes) {

        if(!Variable.flightplayer.contains(player.getPlayer())) {

            if(player.getWorld().getName().equalsIgnoreCase("world")) {

                startFlight(player, minutes);
                return;

            } else {

                player.sendMessage(ChatColor.AQUA + "飛行は建築ワールドのみで使えます");
                return;

            }

        } else {

            return;

        }

    }

    public void startFlight(Player player, Integer minutes) {

        Integer price;
        Integer sumPrice;

        //discount
        if (minutes <= 10) {
            price = price1;
        } else if (minutes <= 30) {
            price = price2;
        } else {
            price = price3;
        }

        //can purchase check
        sumPrice = price * minutes;

        MOFlight econ = new MOFlight();
        Economy economy = econ.getEconomy();
        Double balance = economy.getBalance(player);

        if (balance < sumPrice){
            player.sendMessage(ChatColor.AQUA + "お金が足りません！");
            return;
        }


        //purchase
        player.sendMessage(ChatColor.AQUA + "" + minutes + "分のフライトを " + sumPrice + "MOFU で購入しました！" );
        getLogger().info(player.getName() + " が飛行モードを購入しました | " + minutes + "分");

        economy.withdrawPlayer(player, sumPrice);


        //start flying
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "lp user " + player.getName() + " parent add flight";
        Bukkit.dispatchCommand(console, command);

        player.setFlying(true);
        Variable.flightplayer.add(player.getPlayer());

        Calculation calculation = new Calculation();
        calculation.calcTime(player, minutes);

    }


    public void endFlight(Player player) {

        if (Variable.flightplayer.contains(player.getPlayer())) {

            Variable.flightplayer.remove(player.getPlayer());

            player.sendMessage(ChatColor.AQUA + "" + "フライトが終了しました");
            getLogger().info(player.getName() + " の飛行モードが終了しました");

            player.setFlying(false);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "lp user " + player.getName() + " parent remove flight";
            Bukkit.dispatchCommand(console, command);

        }

    }

}
