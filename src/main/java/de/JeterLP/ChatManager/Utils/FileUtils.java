package de.JeterLP.ChatManager.Utils;

import de.JeterLP.ChatManager.ChatEX;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author TheJeterLP
 */
public class FileUtils {

        public static void writeToFile(String player, String message) {
                if (!Config.LOGCHAT.getBoolean()) return;
                BufferedWriter bw = null;
                File file = new File(ChatEX.getInstance().getDataFolder().getAbsolutePath() + File.separator + "logs");
                if (!file.exists()) {
                        file.mkdir();
                }
                try {
                        bw = new BufferedWriter(new FileWriter(file + File.separator + fileName(), true));
                        bw.write(prefix() + player + ": " + message);
                        bw.newLine();
                } catch (Exception ex) {
                } finally {
                        try {
                                if (bw != null) {
                                        bw.flush();
                                        bw.close();
                                }
                        } catch (Exception ex) {
                        }
                }
        }

        public static String fileName() {
                DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
                Calendar cal = Calendar.getInstance();
                return date.format(cal.getTime()) + ".log";
        }

        public static String prefix() {
                DateFormat date = new SimpleDateFormat("[HH:mm:ss] ");
                Calendar cal = Calendar.getInstance();
                return date.format(cal.getTime());
        }

}
