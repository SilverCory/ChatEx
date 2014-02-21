package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Utils.CommandArgs;
import de.JeterLP.ChatManager.Utils.CommandHelp;
import de.JeterLP.ChatManager.Utils.CommandResult;
import de.JeterLP.ChatManager.Utils.Executor;
import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class ChatExCommand extends Executor {

        public ChatExCommand() {
                command = "chatex";
                permission = "chatex.mod";
                helpPages.add(new CommandHelp("/chatex reload", "Reloads the plugin and its configuration."));
                helpPages.add(new CommandHelp("/chatex clear", "Clears the chat."));
        }

        @Override
        public CommandResult onPlayerCommand(Player p, Command cmd, CommandArgs args) {
                return onServerCommand(p, cmd, args);
        }

        @Override
        public CommandResult onServerCommand(CommandSender sender, Command cmd, CommandArgs args) {
                if (args.getLength() != 1) return CommandResult.ERROR;

                if (args.getString(0).equalsIgnoreCase("reload")) {
                        Bukkit.getPluginManager().disablePlugin(ChatEX.getInstance());
                        Bukkit.getPluginManager().enablePlugin(ChatEX.getInstance());
                        sender.sendMessage("§aConfig was reloaded.");
                        return CommandResult.SUCCESS;
                } else if (args.getString(0).equalsIgnoreCase("clear")) {
                        for (int i = 0; i < 25; i++) {
                                Bukkit.broadcastMessage("\n");
                        }
                        String who = "unknown";
                        if ((sender instanceof ConsoleCommandSender) || (sender instanceof BlockCommandSender)) {
                                who = "CONSOLE";
                        } else if (sender instanceof Player) {
                                who = sender.getName();
                        }
                        Bukkit.broadcastMessage("§aChat has been cleared by " + who);
                        return CommandResult.SUCCESS;
                } else {
                        return CommandResult.ERROR;
                }
        }

}
