package de.JeterLP.ChatManager.Command;

import de.JeterLP.ChatManager.Utils.Locales;
import java.util.Map;
import org.bukkit.command.CommandSender;

/**
 * @author TheJeterLP
 */
public enum CommandResult {

    SUCCESS(null),
    NO_PERMISSION(Locales.COMMAND_RESULT_NO_PERM.getPath()),
    ERROR(Locales.COMMAND_RESULT_WRONG_USAGE.getPath());
    private final String path;

    CommandResult(String path) {
        this.path = path;
    }

    public void sendMessage(CommandSender s, Map<String, String> replace) {
        if (path == null) return;
        Locales.fromPath(path).send(s, replace);
    }
    
    public String getMessage() {
        if(path == null) return null;
        return Locales.fromPath(path).getString();
    }
}
