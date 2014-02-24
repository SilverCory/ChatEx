package de.JeterLP.ChatManager.Utils;

/**
 * @author TheJeterLP
 */
public enum CommandResult {

        SUCCESS(null),
        NO_PERMISSION(Locales.COMMAND_RESULT_NO_PERM.getPath()),
        ERROR(Locales.COMMAND_RESULT_WRONG_USAGE.getPath());
        private final String msg;

        CommandResult(String msg) {
                this.msg = msg;
        }

        public String getMessage() {
                if (msg == null) return null;
                return Locales.fromPath(msg).getString();
        }
}
