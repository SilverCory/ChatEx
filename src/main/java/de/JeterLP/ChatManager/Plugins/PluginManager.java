package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.ChatEX;
import de.JeterLP.ChatManager.Utils.HookManager;
import de.JeterLP.ChatManager.Utils.Utils;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class PluginManager implements PermissionsPlugin {

        private static PermissionsPlugin handler;

        public static PluginManager getInstance() {
                return ChatEX.manager;
        }

        public PluginManager() {
                if (HookManager.getInstance().checkPEX()) {
                        handler = new PermissionsEx();
                } else if (Vault.setupChat() && HookManager.getInstance().checkVault()) {
                        handler = new Vault();
                } else {
                        handler = new Nothing();
                }
        }

        @Override
        public String getName() {
                return handler.getName();
        }

        @Override
        public String getPrefix(Player p) {
                return handler.getPrefix(p);
        }

        @Override
        public String getSuffix(Player p) {
                return handler.getSuffix(p);
        }

        @Override
        public String[] getGroupNames(Player p) {
                return handler.getGroupNames(p);
        }

        @Override
        public String getMessageFormat(Player p) {
                return Utils.replaceColors(handler.getMessageFormat(p));
        }

        @Override
        public String getGlobalMessageFormat(Player p) {
                return Utils.replaceColors(handler.getGlobalMessageFormat(p));
        }
}
