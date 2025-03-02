package dev.siebrenvde.staffutils.api;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.Message;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

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
     * Returns an optional instance of {@link Player} for the provided name
     * @param name the name of the player
     * @return an optional instance of {@link Player}
     */
    Optional<Player> getPlayerByName(String name);

    /**
     * Returns whether the current server is a proxy server
     * @return {@code true} if the server is a proxy server
     */
    default boolean isProxy() {
        return getServerType() == ServerType.VELOCITY || getServerType() == ServerType.BUNGEECORD;
    }

    default Message message(String message) {
        return new LiteralMessage(message);
    }

}
