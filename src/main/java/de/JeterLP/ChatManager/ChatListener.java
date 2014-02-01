package de.JeterLP.ChatManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author TheJeterLP
 */
public abstract class ChatListener implements Listener {

        public void register() {
                Bukkit.getServer().getPluginManager().registerEvents(this, ChatEX.getInstance());
        }

        protected void execute(AsyncPlayerChatEvent event) {
                String format = ChatEX.getManager().getMessageFormat(event.getPlayer());
                boolean localChat = Config.RANGEMODE.getBoolean();
                boolean global = false;
                Player player = event.getPlayer();
                String chatMessage = event.getMessage();
                if (localChat) {
                        if (chatMessage.startsWith("!") && player.hasPermission("chatex.chat.global")) {
                                chatMessage = chatMessage.replaceFirst("!", "");
                                format = ChatEX.getManager().getGlobalMessageFormat(event.getPlayer());
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
