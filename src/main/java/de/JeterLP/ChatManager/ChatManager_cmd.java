package de.JeterLP.ChatManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author TheJeterLP
 */
public class ChatManager_cmd implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender cs, Command cmd, String string, String[] args) {
                if (args.length != 1) {
                        cs.sendMessage("§cWrong usage! Type /chatmanager reload|clearchat");
                }

                if (args[0].equalsIgnoreCase("reload")) {
                        if (!cs.hasPermission("chatmanager.reload")) {
                                cs.sendMessage("§cYou don't have permission.");
                                return true;
                        }
                        Config.reload(false);
                        cs.sendMessage("§aConfig was reloaded.");
                        return true;
                } else if (args[0].equalsIgnoreCase("clearchat")) {
                        for (int i = 0; i < 25; i++) {
                                Bukkit.broadcastMessage("\n");
                        }
                        Bukkit.broadcastMessage("§aChat has been cleared by " + cs.getName());
                        return true;
                } else {
                        cs.sendMessage("§cWrong usage! Type /chatmanager reload|clearchat");
                        return true;
                }
        }

}
