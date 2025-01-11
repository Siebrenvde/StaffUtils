package dev.siebrenvde.staffchat.api;

import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;

import java.util.Optional;

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
     * Returns an optional instance of {@link CommonPlayer} for the provided name
     * @param name the name of the player
     * @return an optional instance of {@link CommonPlayer}
     */
    Optional<CommonPlayer> getPlayerByName(String name);

    /**
     * Returns whether the current server is a proxy server
     * @return {@code true} if the server is a proxy server
     */
    default boolean isProxy() {
        return getServerType() == ServerType.VELOCITY || getServerType() == ServerType.BUNGEECORD;
    }

}
