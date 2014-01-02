package de.JeterLP.ChatManager;

import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author t3hk0d3, TheJeterLP
 */
public class ChatListener implements Listener {

        private final ChatManager main = ChatManager.getInstance();

        @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
        public void onPlayerChat(final AsyncPlayerChatEvent event) throws IOException {
                String format = Config.FORMAT.getString();
                boolean localChat = Config.RANGEMODE.getBoolean();
                boolean global = false;
                Player player = event.getPlayer();
                String chatMessage = event.getMessage();
                if (localChat) {
                        if (chatMessage.startsWith("!") && player.hasPermission("chatex.chat.global")) {
                                chatMessage = chatMessage.replaceFirst("!", "");
                                format = Config.GLOBALFORMAT.getString();
                                global = true;
                        }
                        if (global == false) {
                                event.getRecipients().clear();
                                event.getRecipients().addAll(ChatManager.getUtils().getLocalRecipients(player));
                        }
                }
                format = format.replace("%message", "%2$s").replace("%player", "%1$s");
                format = ChatManager.getUtils().replacePlayerPlaceholders(player, format);
                event.setFormat(format);
                chatMessage = ChatManager.getUtils().translateColorCodes(chatMessage, player);
                event.setMessage(chatMessage);
                main.getFileUtils().log(event.getPlayer().getName(), event.getMessage());
        }
}
