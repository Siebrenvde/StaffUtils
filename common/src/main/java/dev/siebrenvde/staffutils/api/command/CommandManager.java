package dev.siebrenvde.staffutils.api.command;

import java.util.Arrays;

/**
 * A command manager for simple commands
 */
public interface CommandManager {

    /**
     * Registers all commands in the provided array
     * @param commands an array of commands to register
     */
    default void registerAll(BaseCommand... commands) {
        Arrays.stream(commands).forEach(command -> {
            if(command.isEnabled()) register(command);
        });
    }

    /**
     * Registers the provided command
     * @param command the command to register
     */
    void register(BaseCommand command);

}
