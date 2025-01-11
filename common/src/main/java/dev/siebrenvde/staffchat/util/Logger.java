package dev.siebrenvde.staffchat.util;

import dev.siebrenvde.staffchat.config.Config;

public interface Logger {

    /**
     * Logs an info message if the <code>verbose_logging</code> config value is <code>true</code>
     * @param message the message to log
     */
    default void optional(String message) {
        if(Config.CONFIG.verboseLogging) info(message);
    }

    /**
     * Logs an info message
     * @param message the message to log
     */
    void info(String message);

    /**
     * Logs a warning message
     * @param message the message to log
     */
    void warn(String message);

    /**
     * Logs an error message
     * @param message the message to log
     */
    void error(String message);

}
