package de.JeterLP.ChatManager;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @author TheJeterLP
 */
public abstract class ChatListener implements Listener {

        public void register() {
                Bukkit.getServer().getPluginManager().registerEvents(this, ChatEX.getInstance());
        }

}
