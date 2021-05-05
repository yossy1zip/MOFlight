package com.yiorno.moflight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.broadcastMessage;
import static org.bukkit.Bukkit.getLogger;

public class ChangeMode {

    public void startFlight(Player player, Integer time) {

        if(!val.flightplayer.contains(player.getPlayer())) {

            getLogger().info(player.getName() + " が飛行モードになりました | " + time);
            //ImmutableContextSet contextSet = luckPerms.getContextManager().getContext(player);

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "lp user " + player.getName() + " parent add flight";
            Bukkit.dispatchCommand(console, command);

            val.flightplayer.add(player.getPlayer());
        } else {
            return;
        }
    }


    public void endFlight(Player player) {

        if (val.flightplayer.contains(player.getPlayer())) {

            val.flightplayer.remove(player.getPlayer());

            broadcastMessage(ChatColor.YELLOW + player.getName() + " が帰ってきました");

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "lp user " + player.getName() + " parent remove flight";
            Bukkit.dispatchCommand(console, command);

        }

    }

}
