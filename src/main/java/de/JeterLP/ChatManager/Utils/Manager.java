package de.JeterLP.ChatManager.Utils;

import de.JeterLP.ChatManager.ChatEX;
import de.JeterLP.ChatManager.ChatListener;

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

        public static void registerListener(Class<? extends ChatListener> clazz) {
                ChatEX.debug("Starting registering listener: " + clazz.getName());
                try {
                        ChatEX.debug("Making new instance of the listener...");
                        ChatListener listener = clazz.newInstance();
                        ChatEX.debug("Registering listener...");
                        listener.register();
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
        }
}
