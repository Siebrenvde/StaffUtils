package dev.siebrenvde.staffutils.api;

import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface ServerPlatform {

    /**
     * {@return the type of the server implementation}
     */
    ServerType getServerType();

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
    default boolean isProxy() {
        return getServerType() == ServerType.VELOCITY || getServerType() == ServerType.BUNGEECORD;
    }

}
