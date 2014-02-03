package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.Utils.Config;
import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.util.CalculableType;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class bPermissions implements PermissionsPlugin {

        @Override
        public String getName() {
                return "bPermissions";
        }

        @Override
        public String getPrefix(Player p) {
                return ApiLayer.getValue(p.getWorld().getName(), CalculableType.USER, p.getName(), "prefix");
        }

        @Override
        public String getSuffix(Player p) {
                return ApiLayer.getValue(p.getWorld().getName(), CalculableType.USER, p.getName(), "suffix");
        }

        @Override
        public String[] getGroupNames(Player p) {
                return ApiLayer.getGroups(p.getWorld().getName(), CalculableType.USER, p.getName());
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
