package dev.siebrenvde.staffchat.common.minecraft;

import net.kyori.adventure.text.Component;

public interface ServerPlatform {

    enum ServerType {
        SPIGOT,
        VELOCITY,
        BUNGEECORD
    }

    /**
     * {@return the type of the server implementation}
     */
    ServerType getServerType();

    /**
     * Broadcasts a message to all online players with the specified permission
     * @param message The message to broadcast
     * @param permission The required permission
     */
    void broadcast(Component message, String permission);

    /**
     * Returns an instance of {@link CommandSender} corresponding to the provided command sender
     * @param sender The command sender
     * @return An instance of {@link CommandSender}
     * @param <C> The server software's command sender
     */
    <C> CommandSender getCommandSender(C sender);

    /**
     * Returns an instance of {@link Player} corresponding to the provided player
     * @param player the player
     * @return an instance of {@link Player}
     * @param <P> the server software's player
     */
    <P> Player getPlayer(P player);

    /**
     * Returns whether the current server is a proxy server
     * @return {@code true} if the server is a proxy server
     */
    boolean isProxy();

}
