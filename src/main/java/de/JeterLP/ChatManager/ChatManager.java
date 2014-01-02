package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Plugins.PermissionsPlugin;
import de.JeterLP.ChatManager.Plugins.PluginManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.chat.Chat;

/**
 * Original author: t3hk0d3
 * @author TheJeterLP
 */
public class ChatManager extends JavaPlugin {

        private static final Utils utils = new Utils();
        private static HookManager hook;
        private static Chat chat = null;
        private static ChatManager instance;
        private static PermissionsPlugin manager;
        private final FileUtils futils = new FileUtils();

        @Override
        public void onEnable() {
                instance = this;
                Config.load();
                try {
                        futils.init();
                } catch (IOException ex) {
                        getLogger().severe("Error on enabling the chatLogging! " + ex.getMessage());
                }
                if (!Config.ENABLE.getBoolean()) {
                        getServer().getPluginManager().disablePlugin(this);
                        getLogger().info("disabled, check config!");
                        return;
                }
                hook = new HookManager();
                manager = new PluginManager();
                getLogger().info("Successfully hooked into: " + getManager().getName());
                getServer().getPluginManager().registerEvents(new ChatListener(), this);
                getLogger().info("is now enabled!");
        }

        @Override
        public void onDisable() {
                try {
                        futils.stopLogging();
                } catch (IOException ex) {
                        getLogger().severe("Error on disabling the chatLogging! " + ex.getMessage());
                }
                getLogger().info("is now disabled!");
        }

        public static PermissionsPlugin getManager() {
                return manager;
        }

        public static Utils getUtils() {
                return utils;
        }

        public static Chat getChat() {
                return chat;
        }

        public static HookManager getHook() {
                return hook;
        }

        public boolean setupChat() {
                RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
                if (chatProvider != null) {
                        chat = chatProvider.getProvider();
                }
                return (chat != null);
        }

        public static ChatManager getInstance() {
                return instance;
        }

        public FileUtils getFileUtils() {
                return futils;
        }
}
