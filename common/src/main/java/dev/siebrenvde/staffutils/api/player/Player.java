package dev.siebrenvde.staffutils.api.player;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.kyori.adventure.identity.Identity;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;
import java.util.UUID;

@NullMarked
public interface Player extends CommandSender {

    /**
     * Converts the server software's player to a CommonPlayer
     * @param player the player
     * @param <P> the server software's player class
     * @return a new player instance
     */
    static <P> Player of(P player) {
        return StaffUtils.getPlatform().getPlayer(player);
    }

    /**
     * Returns an optional player for the provided name
     * @param name the name of the player
     * @return an optional player instance
     */
    static Optional<Player> byName(String name) { return StaffUtils.getPlatform().getPlayerByName(name); }

    /**
     * {@return the player's UUID}
     */
    default UUID getUniqueId() {
        return get(Identity.UUID).orElseThrow();
    }

}
