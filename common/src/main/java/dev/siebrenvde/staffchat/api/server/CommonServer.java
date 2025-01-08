package dev.siebrenvde.staffchat.api.server;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import net.kyori.adventure.text.Component;

import java.util.List;

public interface CommonServer {

    /**
     * Broadcasts a message to all online players with the specified permission
     * @param message The message to broadcast
     * @param permission The required permission
     */
    default void broadcast(Component message, String permission) {
        getPlayers().forEach(player -> {
            if(player.hasPermission(permission)) {
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
    List<CommonPlayer> getPlayers();

}
