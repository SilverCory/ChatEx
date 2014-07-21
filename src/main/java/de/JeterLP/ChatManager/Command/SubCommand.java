package de.JeterLP.ChatManager.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    protected String perm;

    public SubCommand(String permission) {
        this.perm = permission;
    }

    public String getPermission() {
        return perm;
    }

    public abstract CommandResult executeConsole(CommandSender sender, CommandArgs args) throws Exception;

    public abstract CommandResult executePlayer(Player p, CommandArgs args) throws Exception;

    protected boolean hasPermission(Player player) {
        if (perm == null || perm.isEmpty()) return true;
        return player.hasPermission(perm);
    }

}
