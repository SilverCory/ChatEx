package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Command.BaseCommand;
import de.JeterLP.ChatManager.Command.CommandArgs;
import de.JeterLP.ChatManager.Command.CommandHelp;
import de.JeterLP.ChatManager.Command.CommandResult;
import de.JeterLP.ChatManager.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class ChatExCommand extends BaseCommand {

    public ChatExCommand() {
        super("chatex", "chatex.mod");
        helpPages.add(new CommandHelp("/chatex reload", Locales.COMMAND_RELOAD_DESCRIPTION.getString()));
        helpPages.add(new CommandHelp("/chatex clear", Locales.COMMAND_CLEAR_DESCRIPTION.getString()));
    }

    @Override
    public CommandResult onPlayerCommand(Player p, Command cmd, CommandArgs args) {
        return onServerCommand(p, cmd, args);
    }

    @Override
    public CommandResult onServerCommand(CommandSender sender, Command cmd, CommandArgs args) {
        if (args.getString(0).equalsIgnoreCase("reload")) {
            Bukkit.getPluginManager().disablePlugin(ChatEX.getInstance());
            Bukkit.getPluginManager().enablePlugin(ChatEX.getInstance());
            sender.sendMessage(Locales.MESSAGES_RELOAD.getString());
            return CommandResult.SUCCESS;
        } else if (args.getString(0).equalsIgnoreCase("clear")) {
            for (int i = 0; i < 25; i++) {
                Bukkit.broadcastMessage("\n");
            }
            String who = Locales.COMMAND_CLEAR_UNKNOWN.getString();
            if ((sender instanceof ConsoleCommandSender) || (sender instanceof BlockCommandSender)) {
                who = Locales.COMMAND_CLEAR_CONSOLE.getString();
            } else if (sender instanceof Player) {
                who = sender.getName();
            }
            Bukkit.broadcastMessage(Locales.MESSAGES_CLEAR.getString() + who);
            return CommandResult.SUCCESS;
        } else {
            return CommandResult.ERROR;
        }
    }

    @Override
    public boolean argsCheck(CommandArgs args) {
        return args.getLength() == 1;
    }

}
