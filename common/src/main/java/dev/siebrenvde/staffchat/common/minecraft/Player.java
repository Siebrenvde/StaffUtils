package dev.siebrenvde.staffchat.common.minecraft;

import dev.siebrenvde.staffchat.common.StaffChat;

import java.util.UUID;

public interface Player extends CommandSender {

    /**
     * {@return a new Player instance}
     * @param player the player
     * @param <P> the server software's player class
     */
    static <P> Player of(P player) {
        return (Player) StaffChat.getPlatform().getCommandSender(player);
    }

    /**
     * {@return the player's UUID}
     */
    UUID getUniqueId();

}
