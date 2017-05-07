package de.thejeterlp.chatex.listeners;

import de.thejeterlp.chatex.ChatListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**s
 * @author TheJeterLP
 */
public class NORMAL extends ChatListener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        execute(event);
    }
}
