package de.JeterLP.ChatManager.Utils;

import de.JeterLP.ChatManager.ChatEX;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author TheJeterLP
 */
public enum Locales {

        COMMAND_RELOAD_DESCRIPTION("Commands.Reload.Description", "Reloads the plugin and its configuration."),
        COMMAND_CLEAR_DESCRIPTION("Commands.Clear.Description", "Clears the chat."),
        COMMAND_CLEAR_CONSOLE("Commands.Clear.Console", "CONSOLE"),
        COMMAND_CLEAR_UNKNOWN("Commands.Clear.Unknown", "UNKNOWN"),
        MESSAGES_RELOAD("Messages.Commands.Reload.Success", "&aConfig was reloaded."),
        MESSAGES_CLEAR("Messages.Commands.Clear.Success", "&aThe chat has been cleared by "),
        COMMAND_RESULT_NO_PERM("Messages.CommandResult.NoPermission", "&4[ERROR] &You don't have permission for this! &c(%perm%)"),
        COMMAND_RESULT_WRONG_USAGE("Messages.CommandResult.WrongUsage", "&c[ERROR] &7Wrong usage! Please type &6/%cmd% help&7!");

        private final String value;
        private final String path;
        private static YamlConfiguration cfg;
        private static final File localeFolder = new File(ChatEX.getInstance().getDataFolder().getAbsolutePath() + File.separator + "locales");
        private static final File f = new File(localeFolder, Config.LOCALE.getString() + ".yml");

        private Locales(String path, String val) {
                this.path = path;
                this.value = val;
        }

        public String getPath() {
                return path;
        }

        public String getDefaultValue() {
                return value;
        }

        public String getString() {
                return cfg.getString(path).replaceAll("&((?i)[0-9a-fk-or])", "§$1");
        }

        public static void load() {
                localeFolder.mkdirs();
                reload(false);
                for (Locales c : values()) {
                        if (!cfg.contains(c.getPath())) {
                                c.set(c.getDefaultValue(), false);
                        }
                }
                try {
                        cfg.save(f);
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }

        public void set(Object value, boolean save) {
                cfg.set(path, value);
                if (save) {
                        try {
                                cfg.save(f);
                        } catch (IOException ex) {
                                ex.printStackTrace();
                        }
                        reload(false);
                }
        }

        public static void reload(boolean complete) {
                if (!complete) {
                        cfg = YamlConfiguration.loadConfiguration(f);
                        return;
                }
                load();
        }

        public static Locales fromPath(String path) {
                for (Locales loc : values()) {
                        if (loc.getPath().equalsIgnoreCase(path)) return loc;
                }
                return null;
        }
}
