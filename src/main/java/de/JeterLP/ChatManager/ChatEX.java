package de.JeterLP.ChatManager;

import de.JeterLP.ChatManager.Utils.Config;
import de.JeterLP.ChatManager.Utils.Utils;
import de.JeterLP.ChatManager.Utils.AdvancedUpdater;
import de.JeterLP.ChatManager.Plugins.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

/**
 * @author TheJeterLP
 */
public class ChatEX extends JavaPlugin {

        private static ChatEX instance;
        public static PluginManager manager;

        @Override
        public void onEnable() {
                try {
                        instance = this;
                        Config.load();
                        if (!Config.ENABLE.getBoolean()) {
                                getServer().getPluginManager().disablePlugin(this);
                                getLogger().info("disabled, check config!");
                                return;
                        }
                        saveResource("readme.txt", true);
                        manager = new PluginManager();
                        new Metrics(this).start();
                        new AdvancedUpdater(this, 65863, "http://dev.bukkit.org/bukkit-plugins/chatex/").search();
                        getLogger().info("Successfully hooked into: " + PluginManager.getInstance().getName());
                        if (!Utils.registerListener()) {
                                getLogger().severe("No valid Listener could be found. Please see the reamde.txt for more information.");
                                getServer().getPluginManager().disablePlugin(this);
                        }
                        getCommand("chatmanager").setExecutor(new ChatCommand());
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
}
