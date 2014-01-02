package de.JeterLP.ChatManager.Plugins;

import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class pex implements PermissionsPlugin {

        @Override
        public String getPrefix(Player p, String world, boolean multiPrefixes, boolean PrependPlayerPrefix) {
                PermissionUser user = PermissionsEx.getPermissionManager().getUser(p.getName());
                if (user == null) {
                        return "";
                }
                if (!multiPrefixes) {
                        return user.getPrefix(world);
                }
                String personalPrefix = user.getOwnPrefix();
                if ((!PrependPlayerPrefix) && (personalPrefix != null) && (!personalPrefix.isEmpty())) {
                        return personalPrefix;
                }
                String finalPrefix = "";
                if ((personalPrefix != null) && (!personalPrefix.isEmpty())) {
                        finalPrefix = personalPrefix;
                }
                PermissionGroup[] userGroups = user.getGroups();
                for (PermissionGroup group : userGroups) {
                        String groupPrefix = group.getPrefix();
                        if ((groupPrefix != null) && (!groupPrefix.isEmpty())) {
                                finalPrefix = finalPrefix + groupPrefix;
                        }
                }
                if ((finalPrefix == null) || (finalPrefix.isEmpty())) {
                        finalPrefix = "";
                }
                return finalPrefix;
        }

        @Override
        public String getSuffix(Player p, String world, boolean multiSuffixes, boolean PrependPlayerSuffix) {
                PermissionUser user = PermissionsEx.getPermissionManager().getUser(p.getName());
                if (user == null) {
                        return "";
                }
                if (!multiSuffixes) {
                        return user.getSuffix(world);
                }
                String personalSuffix = user.getOwnSuffix();
                if ((!PrependPlayerSuffix) && (personalSuffix != null) && (!personalSuffix.isEmpty())) {
                        return personalSuffix;
                }
                String finalSuffix = "";
                if ((personalSuffix != null) && (!personalSuffix.isEmpty())) {
                        finalSuffix = personalSuffix;
                }
                PermissionGroup[] userGroups = user.getGroups();
                for (PermissionGroup group : userGroups) {
                        String groupSuffix = group.getSuffix();
                        if ((groupSuffix != null) && (!groupSuffix.isEmpty())) {
                                finalSuffix = finalSuffix + groupSuffix;
                        }
                }
                if ((finalSuffix == null) || (finalSuffix.isEmpty())) {
                        finalSuffix = "";
                }
                return finalSuffix;
        }

        @Override
        public String[] getGroupNames(Player p, String world) {
                PermissionUser user = PermissionsEx.getPermissionManager().getUser(p.getName());
                if (user != null) {
                        return user.getGroupsNames();
                }
                String[] data = {""};
                return data;
        }

        @Override
        public String getName() {
                return PermissionsEx.getPlugin().getDescription().getName();
        }
}
