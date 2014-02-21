package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Plugins.PermissionsPlugin;
import de.JeterLP.ChatManager.Utils.Config;
import de.JeterLP.ChatManager.Utils.Utils;
import de.JeterLP.ChatManager.Utils.AdvancedUpdater;
import de.JeterLP.ChatManager.Plugins.PluginManager;
import de.JeterLP.ChatManager.Utils.Manager;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

/**
 * @author TheJeterLP
 */
public class ChatEX extends JavaPlugin {

        private static ChatEX instance;
        private static PluginManager manager;

        @Override
        public void onEnable() {
                try {
                        instance = this;
                        Config.load();
                        debug("Loaded Config!");
                        if (!Config.ENABLE.getBoolean()) {
                                getServer().getPluginManager().disablePlugin(this);
                                getLogger().info("disabled, check config!");
                                return;
                        }
                        debug("Saving readme.txt to " + getDataFolder().getAbsolutePath());
                        saveResource("readme.txt", true);
                        manager = new PluginManager();
                        debug("Starting Metrics/MCStats...");
                        new Metrics(this).start();
                        debug("Starting updater...");
                        new AdvancedUpdater(this, 65863, "http://dev.bukkit.org/bukkit-plugins/chatex/").search();
                        getLogger().info("Successfully hooked into: " + PluginManager.getInstance().getName());
                        debug("registering Listener...");
                        if (!Utils.registerListener()) {
                                getLogger().severe("No valid Listener could be found. Please see the reamde.txt for more information.");
                                getServer().getPluginManager().disablePlugin(this);
                                return;
                        };
                        Manager.registerCommand(ChatExCommand.class);
                        getLogger().info("is now enabled!");
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        @Override
        public void onDisable() {
                getServer().getScheduler().cancelTasks(this);
                getLogger().info("is now disabled!");
        }

        public static ChatEX getInstance() {
                return instance;
        }

        public static PermissionsPlugin getManager() {
                return manager;
        }

        public static void debug(String message) {
                if (!Config.DEBUG.getBoolean()) return;
                String output = "[DEBUG] " + message;
                getInstance().getLogger().log(Level.FINE, output);
        }
}
