package de.thejeterlp.chatex.listeners;

import de.thejeterlp.chatex.ChatListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author TheJeterLP
 */
public class HIGHEST extends ChatListener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        execute(event);
    }
}
