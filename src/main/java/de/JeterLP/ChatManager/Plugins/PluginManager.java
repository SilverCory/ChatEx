package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.ChatEX;
import de.JeterLP.ChatManager.HookManager;
import de.JeterLP.ChatManager.Utils;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class PluginManager implements PermissionsPlugin {

        private final PermissionsPlugin handler;
        private final ChatEX manager = ChatEX.getInstance();

        public PluginManager() {
                if (HookManager.getInstance().checkPEX()) {
                        handler = new pex();
                } else if (manager.setupChat() && HookManager.getInstance().checkVault()) {
                        handler = new Vault();
                } else {
                        handler = new noPermPlugin();
                }
        }

        @Override
        public String getName() {
                return handler.getName();
        }

        @Override
        public String getPrefix(Player p, String world, boolean multiPrefixes, boolean PrependPlayerPrefix) {
                return handler.getPrefix(p, world, multiPrefixes, PrependPlayerPrefix);
        }

        @Override
        public String getSuffix(Player p, String world, boolean multiSuffixes, boolean PrependPlayerSuffix) {
                return handler.getSuffix(p, world, multiSuffixes, PrependPlayerSuffix);
        }

        @Override
        public String[] getGroupNames(Player p, String world) {
                return handler.getGroupNames(p, world);
        }

        @Override
        public String getMessageFormat(Player p) {
                return Utils.getInstance().replaceColors(handler.getMessageFormat(p));
        }

        @Override
        public String getGlobalMessageFormat(Player p) {
                return Utils.getInstance().replaceColors(handler.getGlobalMessageFormat(p));
        }
}
