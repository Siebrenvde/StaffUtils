package dev.siebrenvde.staffutils.api.server;

import dev.siebrenvde.staffutils.api.player.Player;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;

@NullMarked
public interface Server extends ForwardingAudience {

    /**
     * Broadcasts a message to all online players with the specified permission
     * @param message The message to broadcast
     * @param permission The required permission
     */
    default void broadcast(Component message, String permission) {
        getPlayers().forEach(player -> {
            if (player.hasPermission(permission)) {
                player.sendMessage(message);
            }
        });
    }

    /**
     * {@return the name of the server}
     */
    String getName();

    /**
     * {@return a list of connected players}
     */
    List<Player> getPlayers();

    /**
     * Returns an optional instance of {@link Player} for the provided name
     * @param name the name of the player
     * @return an optional instance of {@link Player}
     */
    Optional<Player> getPlayer(String name);

}
