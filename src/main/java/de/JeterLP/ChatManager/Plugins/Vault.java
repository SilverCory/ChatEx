package de.JeterLP.ChatManager.Plugins;

import de.JeterLP.ChatManager.ChatEX;
import org.bukkit.entity.Player;

/**
 * @author TheJeterLP
 */
public class Vault implements PermissionsPlugin {

    @Override
    public String getPrefix(Player p, String world, boolean multiPrefixes, boolean PrependPlayerPrefix) {
        return ChatEX.getChat().getPlayerPrefix(world, p.getName());
    }

    @Override
    public String getSuffix(Player p, String world, boolean multiSuffixes, boolean PrependPlayerSuffix) {
        return ChatEX.getChat().getPlayerSuffix(world, p.getName());
    }

    @Override
    public String[] getGroupNames(Player p, String world) {
        return ChatEX.getChat().getPlayerGroups(world, p.getName());
    }
    
    @Override
    public String getName() {
        return ChatEX.getChat().getName();
    }
}
