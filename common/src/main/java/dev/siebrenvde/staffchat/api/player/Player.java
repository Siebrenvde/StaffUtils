package dev.siebrenvde.staffchat.api.player;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.command.CommandSender;

import java.util.UUID;

public interface Player extends CommandSender {

    /**
     * {@return a new Player instance}
     * @param player the player
     * @param <P> the server software's player class
     */
    static <P> Player of(P player) {
        return StaffChat.getPlatform().getPlayer(player);
    }

    /**
     * {@return the player's UUID}
     */
    UUID getUniqueId();

}
