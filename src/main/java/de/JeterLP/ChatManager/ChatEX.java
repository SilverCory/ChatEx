package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Plugins.PermissionsPlugin;
import de.JeterLP.ChatManager.Plugins.PluginManager;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

/**
 * Original author: t3hk0d3
 * @author TheJeterLP
 */
public class ChatEX extends JavaPlugin {

        private static ChatEX instance;
        private static PermissionsPlugin manager;
        private Metrics m;
        private AdvancedUpdater updater;

        @Override
        public void onEnable() {
                instance = this;
                Config.load();
                if (!Config.ENABLE.getBoolean()) {
                        getServer().getPluginManager().disablePlugin(this);
                        getLogger().info("disabled, check config!");
                        return;
                }
                try {
                        m = new Metrics(this);
                        m.start();
                } catch (IOException ex) {
                }
                try {
                        updater = new AdvancedUpdater(this, 65863, "http://dev.bukkit.org/bukkit-plugins/chatex/");
                        updater.search();
                } catch (Exception ex) {
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

       
        public static ChatEX getInstance() {
                return instance;
        }
}
