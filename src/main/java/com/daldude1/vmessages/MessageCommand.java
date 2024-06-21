package com.daldude1.vmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private final Main main;

    public MessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings.length == 2) {
                Player target = Bukkit.getPlayerExact(strings[0]);

                if (target != player && target != null) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < strings.length; i++) {
                        builder.append(strings[i]).append(" ");
                    }

                    String send_msg = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("send.user_send"))
                            .replace("<user>", target.getName())
                            .replace("<message>", builder);

                    player.sendMessage(send_msg);

                    String receive_msg = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("send.user_receive"))
                            .replace("<user>", player.getName())
                            .replace("<message>", builder);

                    target.sendMessage(receive_msg);

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("errors.user_offline")));
                }

            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("errors.arguments_error")));
            }
        }

        return false;
    }

}
