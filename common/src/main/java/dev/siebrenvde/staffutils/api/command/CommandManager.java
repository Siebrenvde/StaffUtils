package dev.siebrenvde.staffutils.api.command;

import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * A command manager for Brigadier commands
 * @param <C> The server software's command sender
 */
@NullMarked
public interface CommandManager<C> {

    /**
     * Registers all commands in the provided array
     * @param commands an array of commands to register
     */
    default void registerAll(List<BaseCommand<C>> commands) {
        commands.forEach(command -> {
            if(command.isEnabled()) register(command);
        });
    }

    /**
     * Registers the provided command
     * @param command the command to register
     */
    void register(BaseCommand<C> command);

}
