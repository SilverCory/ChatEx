package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.Utils.Config;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author TheJeterLP
 */
public class Vault implements PermissionsPlugin {

        private static Chat chat = null;

        @Override
        public String getPrefix(Player p) {
                if (!Config.MULTIPREFIXES.getBoolean()) {
                        return chat.getPlayerPrefix(p.getWorld(), p.getName());
                }
                String finalPrefix = "";
                for (String group : chat.getPlayerGroups(p)) {
                        String groupPrefix = chat.getGroupPrefix(p.getWorld(), group);
                        if (groupPrefix != null && !groupPrefix.isEmpty()) {
                                finalPrefix += groupPrefix;
                        }
                }
                return finalPrefix;
        }

        @Override
        public String getSuffix(Player p) {
                 if (!Config.MULTIPREFIXES.getBoolean()) {
                        return chat.getPlayerSuffix(p.getWorld(), p.getName());
                }
                String finalSuffix = "";
                for (String group : chat.getPlayerGroups(p)) {
                        String groupSuffix = chat.getGroupSuffix(p.getWorld(), group);
                        if (groupSuffix != null && !groupSuffix.isEmpty()) {
                                finalSuffix += groupSuffix;
                        }
                }
                return finalSuffix;
        }

        @Override
        public String[] getGroupNames(Player p) {
                return chat.getPlayerGroups(p.getWorld(), p.getName());
        }

        @Override
        public String getName() {
                return chat.getName();
        }

        @Override
        public String getMessageFormat(Player p) {
                return Config.FORMAT.getString();
        }

        @Override
        public String getGlobalMessageFormat(Player p) {
                return Config.GLOBALFORMAT.getString();
        }

        public static boolean setupChat() {
                try {
                        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
                        if (chatProvider != null) {
                                chat = chatProvider.getProvider();
                        }
                        return (chat != null);
                } catch (Throwable e) {
                        return false;
                }
        }
}
