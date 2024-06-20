package com.daldude1.vmessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private Main main;

    public MessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Object sender;
        // Check if the sender of the command is a Player

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings.length == 2) {
                Player target = Bukkit.getPlayerExact(strings[0]);

                if (target != null) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < strings.length; i++) {
                        builder.append(strings[i]).append(" ");
                    }

                    player.sendMessage("You -> " + target.getName() + ": " + builder);
                    target.sendMessage(player.getName() + "-> You: " + builder);

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage("El usuario no está en linea.");
                }

            } else {
                player.sendMessage("Debes agregar los argumentos.");
            }
        }

        return false;
    }

}
