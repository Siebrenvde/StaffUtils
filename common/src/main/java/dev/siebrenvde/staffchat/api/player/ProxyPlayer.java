package dev.siebrenvde.staffchat.api.player;

import dev.siebrenvde.staffchat.StaffChat;

public interface ProxyPlayer extends Player {

    /**
     * {@return a new ProxyPlayer instance}
     * @param player the player
     * @param <P> the server software's player class
     */
    static <P> ProxyPlayer of(P player) {
        return (ProxyPlayer) StaffChat.getPlatform().getPlayer(player);
    }

    /**
     * {@return the name of the server the player is connected to}
     */
    String getServerName();

    /**
     * {@return the protocol version of the player}
     */
    int getProtocolVersion();

}
