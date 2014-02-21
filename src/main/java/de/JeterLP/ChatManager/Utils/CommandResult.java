package de.JeterLP.ChatManager.Utils;

import org.bukkit.ChatColor;

/**
 * @author TheJeterLP
 */
public enum CommandResult {

      SUCCESS(null),
      NO_PERMISSION(ChatColor.DARK_RED + "[ERROR] " + ChatColor.GRAY + "You don't have permission for this! " + ChatColor.RED + "(%perm%)"),
      ERROR(ChatColor.RED + "[ERROR] " + ChatColor.GRAY + "Wrong usage! Please type " + ChatColor.GOLD + "/%cmd% help " + ChatColor.GRAY + "!");
      private final String msg;

      CommandResult(String msg) {
          this.msg = msg;
      }

      public String getMessage() {
          return this.msg;
      }
} 

