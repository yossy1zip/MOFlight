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
            startFlight(player, minutes);
            return;
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


        //purchase
        player.sendMessage(ChatColor.AQUA + "" + minutes + "分のフライトを " + sumPrice + "MOFU で購入しました！" );
        getLogger().info(player.getName() + " が飛行モードを購入しました | " + minutes + "分");

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "lp user " + player.getName() + " parent add flight";
        Bukkit.dispatchCommand(console, command);

        Variable.flightplayer.add(player.getPlayer());

        Calculation calculation = new Calculation();
        calculation.calcTime(player, minutes);

    }


    public void endFlight(Player player) {

        if (Variable.flightplayer.contains(player.getPlayer())) {

            Variable.flightplayer.remove(player.getPlayer());

            player.sendMessage(ChatColor.AQUA + "" + "フライトが終了しました");
            getLogger().info(player.getName() + " の飛行モードが終了しました");

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "lp user " + player.getName() + " parent remove flight";
            Bukkit.dispatchCommand(console, command);

        }

    }

}
