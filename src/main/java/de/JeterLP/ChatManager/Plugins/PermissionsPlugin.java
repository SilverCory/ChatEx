package de.JeterLP.ChatManager.Plugins;

import org.bukkit.entity.Player;

/**
 *
 * @author TheJeterLP
 */
public interface PermissionsPlugin {

    public String getName();

    public String getPrefix(Player p, String world, boolean multiPrefixes, boolean PrependPlayerPrefix);

    public String getSuffix(Player p, String world, boolean multiSuffixes, boolean PrependPlayerSuffix);

    public String[] getGroupNames(Player p, String world);
    
    public String getMessageFormat(Player p);
    
    public String getGlobalMessageFormat(Player p);
}
