package com.daldude1.vmessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    private Main main;

    public ReplyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings.length >= 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());

                    Player target = Bukkit.getPlayer(uuid);
                    if (target != null) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < strings.length; i++) {
                            builder.append(strings[i]).append(" ");
                        }

                        player.sendMessage("You -> " + target.getName() + ": " + builder);
                        target.sendMessage(player.getName() + "-> You: " + builder);
                    } else {
                        player.sendMessage("La persona qué te envio un mensaje ya no está en linea");
                    }
                } else {
                    player.sendMessage("No te han enviado un mensaje");
                }
            } else {
                player.sendMessage("Necesitas agregar argumenteos");
            }
        }

        return true;
    }

}
