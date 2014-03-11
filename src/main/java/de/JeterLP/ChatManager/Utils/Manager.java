package de.JeterLP.ChatManager.Utils;

import de.JeterLP.ChatManager.Command.Executor;
import de.JeterLP.ChatManager.ChatEX;

/**
 * @author TheJeterLP
 */
public class Manager {

        public static void registerCommand(Class<? extends Executor> clazz) {
                ChatEX.debug("Starting registering command: " + clazz.getName());
                try {
                        ChatEX.debug("Making new instance of Command...");
                        Executor e = clazz.newInstance();
                        ChatEX.debug("Preparing command...");
                        e.prepare();
                        if (e.getBukkitCommand() != null) {
                                ChatEX.debug("Setting executor of command...");
                                e.getBukkitCommand().setExecutor(e);
                        } else {
                                ChatEX.getInstance().getLogger().severe("BukkitCommand is null or empty! Check " + clazz.getName());
                        }
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
        }
}
