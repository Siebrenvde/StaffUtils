package dev.siebrenvde.staffchat.api;

import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
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
     * Returns an instance of {@link CommonCommandSender} corresponding to the provided command sender
     * @param sender The command sender
     * @return An instance of {@link CommonCommandSender}
     * @param <C> The server software's command sender
     */
    <C> CommonCommandSender getCommandSender(C sender);

    /**
     * Returns an instance of {@link CommonPlayer} corresponding to the provided player
     * @param player the player
     * @return an instance of {@link CommonPlayer}
     * @param <P> the server software's player
     */
    <P> CommonPlayer getPlayer(P player);

    /**
     * Returns whether the current server is a proxy server
     * @return {@code true} if the server is a proxy server
     */
    boolean isProxy();

}
