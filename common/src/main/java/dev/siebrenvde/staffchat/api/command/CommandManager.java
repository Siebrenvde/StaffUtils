package dev.siebrenvde.staffchat.api.command;

/**
 * A command manager for simple commands
 */
public interface CommandManager {

    /**
     * Registers the provided command
     * @param command the command to register
     */
    void register(BaseCommand command);

}
