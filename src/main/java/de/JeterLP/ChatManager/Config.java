package de.JeterLP.ChatManager;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventPriority;

/**
 * @author TheJeterLP
 */
public enum Config {

        ENABLE("enable", true, "Should the plugin be enabled?"),
        FORMAT("message-format", "%prefix%player%suffix: %message", "The standar message-format"),
        GLOBALFORMAT("global-message-format", "&9[%world] %prefix%player%suffix: &e%message", "The message-format if ranged-mode is enabled."),
        RANGEMODE("ranged-mode", false, "Should the ranged-mode be enabled?"),
        RANGE("chat-range", 100.0, "The range to talk to other players"),
        MULTIPREFIXES("multi-prefixes", false, "Should the multi-prefixes be enabled? (only PermissionsEx)"),
        MULTISUFFIXES("multi-suffixes", false, "Should the multi-suffixes be enabled? (only PermissionsEx)"),
        PREPENDPREFIX("prepend-player-prefix", false, "Should the Players prefix be prepended? (only PermissionsEx)"),
        PREPENDSUFFIX("prepend-player-suffix", false, "Should the Players suffix be prepended? (only PermissionsEx)"),
        LOGCHAT("logChat", true, "Should the chat be logged?"),
        EVENTPRIORITY("Priority", EventPriority.LOWEST.toString(), "EventPriority for the ChatListener. " + EventPriority.LOWEST.toString() + " Will be executed first. " + EventPriority.HIGHEST.toString() + " will be executed last. \n"
                        + "Possible Prioritys: " + getPrioritys());

        private final Object value;
        private final String path;
        private final String description;
        private static YamlConfiguration cfg;
        private static final File f = new File(ChatEX.getInstance().getDataFolder(), "config.yml");

        private Config(String path, Object val, String description) {
                this.path = path;
                this.value = val;
                this.description = description;
        }

        private static String getPrioritys() {
                String t = "";
                for (EventPriority pr : EventPriority.values()) {
                        if (pr == EventPriority.MONITOR) continue;
                        t += pr.toString() + ", ";
                }
                return t;
        }

        public String getPath() {
                return path;
        }

        public String getDescription() {
                return description;
        }

        public Object getDefaultValue() {
                return value;
        }

        public boolean getBoolean() {
                return cfg.getBoolean(path);
        }

        public double getDouble() {
                return cfg.getDouble(path);
        }

        public String getString() {
                return cfg.getString(path).replaceAll("&((?i)[0-9a-fk-or])", "§$1");
        }
        
        public static void load() {
                ChatEX.getInstance().getDataFolder().mkdirs();
                reload(false);
                String header = "";
                for (Config c : values()) {
                        header += c.getPath() + ": " + c.getDescription() + System.lineSeparator();
                        if (!cfg.contains(c.getPath())) {
                                cfg.set(c.getPath(), c.getDefaultValue());
                        }
                }
                cfg.options().header(header);
                try {
                        cfg.save(f);
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }

        public void set(Object value) {
                cfg.set(path, value);
                try {
                        cfg.save(f);
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
                reload(false);
        }

        public static void reload(boolean complete) {
                if (!complete) {
                        cfg = YamlConfiguration.loadConfiguration(f);
                        return;
                }
                load();
        }
}
