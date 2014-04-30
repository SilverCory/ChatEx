package de.JeterLP.ChatManager.Command;

import de.JeterLP.ChatManager.ChatEX;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public abstract class Executor implements CommandExecutor {

    protected String permission;
    protected String command;
    protected final List<CommandHelp> helpPages = new ArrayList<CommandHelp>();
    private final List<String> HELP_TEXT = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1 && (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) && !HELP_TEXT.isEmpty()) {
            for (String s : HELP_TEXT) {
                sender.sendMessage(s);
            }
            return true;
        } else {
            CommandResult cr;
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!hasPermission(p, false)) {
                    cr = CommandResult.NO_PERMISSION;
                } else {
                    cr = onPlayerCommand(p, cmd, new CommandArgs(args));
                }
            } else {
                cr = onServerCommand(sender, cmd, new CommandArgs(args));
            }

            if (cr != null && cr.getMessage() != null) {
                Map<String, String> replace = new HashMap<String, String>();
                replace.put("%cmd%", cmd.getName());
                if (permission != null) {
                    replace.put("%perm%", permission);
                } else {
                    replace.put("%perm%", "");
                }
                cr.sendMessage(sender, replace);
            }
            return true;
        }

    }

    public abstract CommandResult onPlayerCommand(Player p, Command cmd, CommandArgs args);

    public abstract CommandResult onServerCommand(CommandSender sender, Command cmd, CommandArgs args);

    public void prepare() {
        if (helpPages == null || helpPages.isEmpty()) {
            return;
        }
        HELP_TEXT.add(ChatColor.GREEN + "------------------------" + ChatColor.BLUE + "Help" + ChatColor.GREEN + "-------------------------");
        for (CommandHelp ch : helpPages) {
            HELP_TEXT.add(ch.getText());
        }
        HELP_TEXT.add(ChatColor.GREEN + "-----------------------------------------------------");
    }

    protected boolean hasPermission(Player player, boolean other) {
        if (permission == null || permission.isEmpty()) return true;
        String perm = permission;
        if (other) {
            perm += ".other";
        }
        return player.hasPermission(perm) || player.hasPermission("chatex.*") || player.isOp();
    }

    public PluginCommand getBukkitCommand() {
        if (command == null || command.isEmpty()) {
            return null;
        }
        return ChatEX.getInstance().getCommand(command);
    }
}
