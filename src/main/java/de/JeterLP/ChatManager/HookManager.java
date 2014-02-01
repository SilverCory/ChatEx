package de.JeterLP.ChatManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author TheJeterLP
 */
public class HookManager {

        private static final HookManager INSTANCE = new HookManager();

        private HookManager() {
        }

        public static HookManager getInstance() {
                return INSTANCE;
        }

        public boolean checkVault() {
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Vault");
                return plugin != null && plugin.isEnabled();
        }

        public boolean checkFactions() {
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Factions");
                return plugin != null && plugin.isEnabled();
        }

        public boolean checkPEX() {
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx");
                return plugin != null && plugin.isEnabled();
        }

        public boolean checkMVCore() {
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
                return plugin != null && plugin.isEnabled();
        }

        public boolean checkEssentials() {
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Essentials");
                return plugin != null;
        }

}
