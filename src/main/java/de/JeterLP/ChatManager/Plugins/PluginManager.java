package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.ChatManager;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class PluginManager implements PermissionsPlugin {

        private final PermissionsPlugin handler;
        private final ChatManager manager = ChatManager.getInstance();

        public PluginManager() {
                if (ChatManager.getHook().checkPEX()) {
                        handler = new pex();
                } else if (manager.setupChat() && ChatManager.getHook().checkVault()) {
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
}
