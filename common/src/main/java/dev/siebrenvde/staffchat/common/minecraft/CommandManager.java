package dev.siebrenvde.staffchat.common.minecraft;

import dev.siebrenvde.staffchat.common.minecraft.commands.BaseCommand;

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
