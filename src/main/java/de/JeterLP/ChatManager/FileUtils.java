package de.JeterLP.ChatManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author TheJeterLP
 */
public class FileUtils {

        private BufferedWriter w;
        private File log;

        public void init() throws IOException {
                if (!Config.LOGCHAT.getBoolean()) return;
                log = new File(ChatManager.getInstance().getDataFolder().getAbsolutePath() + File.separator + "logs" + File.separator + getMonth() + ".log");
                if (!log.exists()) {
                        log.createNewFile();
                        w = new BufferedWriter(new FileWriter(log));
                        return;
                }
                w = new BufferedWriter(new FileWriter(log));
                w.newLine();

        }

        public void log(String player, String message) throws IOException {
                if (!Config.LOGCHAT.getBoolean()) return;
                w.write(getTime() + player + ": " + message);
                w.flush();
                w.newLine();
        }

        public void stopLogging() throws IOException {
                if (!Config.LOGCHAT.getBoolean()) return;
                w.close();
        }

        private String getMonth() {
                Calendar c = Calendar.getInstance();
                int month = c.get(Calendar.MONTH);
                month++;
                int year = c.get(Calendar.YEAR);
                int day = c.get(Calendar.DAY_OF_MONTH);
                return day + "-" + month + "-" + year;
        }

        private String getTime() {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                return hour + ":" + minute + " ";
        }

}
