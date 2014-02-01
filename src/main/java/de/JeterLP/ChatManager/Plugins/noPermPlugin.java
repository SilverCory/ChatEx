package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.Config;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class noPermPlugin implements PermissionsPlugin {

        @Override
        public String getPrefix(Player p, String world, boolean multiPrefixes, boolean PrependPlayerPrefix) {
                return "";
        }

        @Override
        public String getSuffix(Player p, String world, boolean multiSuffixes, boolean PrependPlayerSuffix) {
                return "";
        }

        @Override
        public String[] getGroupNames(Player p, String world) {
                String[] data = {""};
                return data;
        }

        @Override
        public String getName() {
                return "Nothing was found!";
        }

        @Override
        public String getMessageFormat(Player p) {
                return Config.FORMAT.getString();
        }

        @Override
        public String getGlobalMessageFormat(Player p) {
                return Config.GLOBALFORMAT.getString();
        }

}
