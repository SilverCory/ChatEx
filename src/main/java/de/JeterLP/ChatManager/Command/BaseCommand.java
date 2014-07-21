package de.JeterLP.ChatManager.Command;

import de.JeterLP.ChatManager.ChatEX;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public abstract class BaseCommand implements CommandExecutor {

    private final String permission;
    private String subPerm;
    private final String command;
    protected final List<CommandHelp> helpPages = new ArrayList<CommandHelp>();
    private final List<String> HELP_TEXT = new ArrayList<String>();
    protected final HashMap<String, SubCommand> subcmds = new HashMap<String, SubCommand>();

    protected BaseCommand(String command, String permission) {
        this.command = command;
        this.permission = permission;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sendHelp(sender, args)) return true;
        CommandResult cr = null;
        if (!argsCheck(CommandArgs.getArgs(args, 0))) {
            cr = CommandResult.ERROR;
        } else {
            cr = execute(cmd, args, sender);
        }
        sendMessages(sender, cmd, cr);
        return true;
    }

    public abstract CommandResult onPlayerCommand(Player p, Command cmd, CommandArgs args) throws Exception;

    public abstract CommandResult onServerCommand(CommandSender sender, Command cmd, CommandArgs args) throws Exception;

    public abstract boolean argsCheck(CommandArgs args);

    public void prepare() {
        if (helpPages == null || helpPages.isEmpty()) return;
        HELP_TEXT.add(ChatColor.GREEN + "------------------------" + ChatColor.BLUE + "Help" + ChatColor.GREEN + "-------------------------");
        for (CommandHelp ch : helpPages) {
            HELP_TEXT.add(ch.getText());
        }
        HELP_TEXT.add(ChatColor.GREEN + "-----------------------------------------------------");
    }

    protected boolean hasPermission(Player player) {
        if (subPerm == null || subPerm.isEmpty()) {
            if (permission == null || permission.isEmpty()) return true;
            return player.hasPermission(permission) || player.isOp();
        } else {
            return player.hasPermission(subPerm) || player.isOp();
        }
    }

    private PluginCommand getBukkitCommand() {
        if (command == null || command.isEmpty()) {
            return null;
        }
        return ChatEX.getInstance().getCommand(command);
    }

    public void register() {
        getBukkitCommand().setExecutor(this);
    }

    protected boolean sendHelp(CommandSender s, String[] args) {
        if (args.length == 1 && (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) && !HELP_TEXT.isEmpty()) {
            for (String string : HELP_TEXT) {
                s.sendMessage(string);
            }
            return true;
        }
        return false;
    }

    private CommandResult execute(Command cmd, String[] args, CommandSender sender) {
        subPerm = null;
        CommandResult cr;
        try {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length >= 1) {
                    if (subcmds.containsKey(args[0].toLowerCase())) {
                        SubCommand sub = subcmds.get(args[0].toLowerCase());
                        subPerm = sub.getPermission();
                        if (!hasPermission(p)) {
                            cr = CommandResult.NO_PERMISSION;
                        } else {
                            cr = sub.executePlayer(p, CommandArgs.getArgs(args, 1));
                            subPerm = sub.getPermission();
                        }
                    } else {
                        if (!hasPermission(p)) {
                            cr = CommandResult.NO_PERMISSION;
                        } else {
                            cr = onPlayerCommand(p, cmd, CommandArgs.getArgs(args, 0));
                        }
                    }
                } else {
                    if (!hasPermission(p)) {
                        cr = CommandResult.NO_PERMISSION;
                    } else {
                        cr = onPlayerCommand(p, cmd, CommandArgs.getArgs(args, 0));
                    }
                }
            } else {
                if (args.length >= 1) {
                    if (subcmds.containsKey(args[0].toLowerCase())) {
                        SubCommand sub = subcmds.get(args[0].toLowerCase());
                        cr = sub.executeConsole(sender, CommandArgs.getArgs(args, 1));
                    } else {
                        cr = onServerCommand(sender, cmd, CommandArgs.getArgs(args, 0));
                    }
                } else {
                    cr = onServerCommand(sender, cmd, CommandArgs.getArgs(args, 0));
                }
            }
        } catch (Exception e) {
            sender.sendMessage("§4The command generated an exception.");
            e.printStackTrace();
            cr = CommandResult.SUCCESS;
        }
        return cr;
    }

    private void sendMessages(CommandSender sender, Command cmd, CommandResult cr) {
        if (cr != null && cr.getMessage() != null) {
            String perm;
            if (subPerm != null) {
                perm = subPerm;
            } else if (permission != null) {
                perm = permission;
            } else {
                perm = "";
            }
            sender.sendMessage(cr.getMessage().replace("%cmd%", cmd.getName()).replace("%perm", perm));
        }
    }

    public String getPermission() {
        return permission;
    }
}
