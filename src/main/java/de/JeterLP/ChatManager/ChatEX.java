package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Plugins.PermissionsPlugin;
import de.JeterLP.ChatManager.Plugins.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.chat.Chat;

/**
 * Original author: t3hk0d3
 * @author TheJeterLP
 */
public class ChatEX extends JavaPlugin {

        private static Chat chat = null;
        private static ChatEX instance;
        private static PermissionsPlugin manager;

        @Override
        public void onEnable() {
                instance = this;
                Config.load();
                if (!Config.ENABLE.getBoolean()) {
                        getServer().getPluginManager().disablePlugin(this);
                        getLogger().info("disabled, check config!");
                        return;
                }
                manager = new PluginManager();
                getLogger().info("Successfully hooked into: " + getManager().getName());
                Utils.registerListener();
                getCommand("chatmanager").setExecutor(new ChatManager_cmd());
                getLogger().info("is now enabled!");
        }

        @Override
        public void onDisable() {
                getLogger().info("is now disabled!");
        }

        public static PermissionsPlugin getManager() {
                return manager;
        }

        public static Chat getChat() {
                return chat;
        }

        public boolean setupChat() {
                try {
                        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
                        if (chatProvider != null) {
                                chat = chatProvider.getProvider();
                        }
                        return (chat != null);
                } catch (Exception e) {
                        return false;
                }
        }

        public static ChatEX getInstance() {
                return instance;
        }
}
