package de.JeterLP.ChatManager.Utils;

import de.JeterLP.ChatManager.ChatEX;
import de.JeterLP.ChatManager.Command.BaseCommand;

/**
 * @author TheJeterLP
 */
public class Manager {

    public static void registerCommand(Class<? extends BaseCommand> clazz) {
        ChatEX.debug("Starting registering command: " + clazz.getName());
        try {
            ChatEX.debug("Making new instance of Command...");
            BaseCommand bc = clazz.newInstance();
            ChatEX.debug("Preparing command...");
            bc.prepare();
            ChatEX.debug("Registering command...");
            bc.register();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
