package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.util.Logger;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SpigotLogger implements Logger {

    private final java.util.logging.Logger logger;

    public SpigotLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warning(message);
    }

    @Override
    public void error(String message) {
        logger.severe(message);
    }

}
