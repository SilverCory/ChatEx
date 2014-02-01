package de.JeterLP.ChatManager.Listeners;

import de.JeterLP.ChatManager.ChatListener;
import de.JeterLP.ChatManager.Config;
import de.JeterLP.ChatManager.FileUtils;
import de.JeterLP.ChatManager.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author t3hk0d3, TheJeterLP
 */
public class LOWEST extends ChatListener {

        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        public void onPlayerChat(final AsyncPlayerChatEvent event) {
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
                        if (!global) {
                                event.getRecipients().clear();
                                event.getRecipients().addAll(Utils.getInstance().getLocalRecipients(player));
                        }
                }
                format = format.replace("%message", "%2$s").replace("%player", "%1$s");
                format = Utils.getInstance().replacePlayerPlaceholders(player, format);
                event.setFormat(format);
                chatMessage = Utils.getInstance().translateColorCodes(chatMessage, player);
                event.setMessage(chatMessage);
                FileUtils.writeToFile(event.getPlayer().getName(), event.getMessage());
        }
}
