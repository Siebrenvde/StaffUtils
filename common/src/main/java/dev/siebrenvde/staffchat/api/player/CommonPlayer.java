package dev.siebrenvde.staffchat.api.player;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;

import java.util.Optional;
import java.util.UUID;

public interface CommonPlayer extends CommonCommandSender {

    /**
     * Converts the server software's player to a CommonPlayer
     * @param player the player
     * @param <P> the server software's player class
     * @return a new player instance
     */
    static <P> CommonPlayer of(P player) {
        return StaffChat.getPlatform().getPlayer(player);
    }

    /**
     * Returns an optional player for the provided name
     * @param name the name of the player
     * @return an optional player instance
     */
    static Optional<CommonPlayer> byName(String name) { return StaffChat.getPlatform().getPlayerByName(name); }

    /**
     * {@return the player's UUID}
     */
    UUID getUniqueId();

}
