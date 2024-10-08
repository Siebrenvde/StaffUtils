package dev.siebrenvde.staffchat.common.minecraft;

import net.kyori.adventure.text.Component;

public interface MinecraftPlugin {

    /**
     * Broadcasts a message to all online players with the specified permission
     * @param message The message to broadcast
     * @param permission The required permission
     */
    void broadcast(Component message, String permission);

    /**
     * Returns an instance of {@link MinecraftCommandSender} corresponding to the provided command sender
     * @param sender The command sender
     * @return An instance of {@link MinecraftCommandSender}
     * @param <C> The server software's command sender
     */
    <C> MinecraftCommandSender getCommandSender(C sender);

}
