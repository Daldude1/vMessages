package com.daldude1.vmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    private final Main main;

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
                        for (String string : strings) {
                            builder.append(string).append(" ");
                        }

                        String send_msg = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("send.user_send"))
                                .replace("<user>", target.getName())
                                .replace("<message>", builder);

                        player.sendMessage(send_msg);

                        String receive_msg = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("send.user_receive"))
                                .replace("<user>", player.getName())
                                .replace("<message>", builder);

                        target.sendMessage(receive_msg);
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("errors.user_offline")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("errors.no_reply")));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("errors.arguments_error")));
            }
        }

        return true;
    }

}
