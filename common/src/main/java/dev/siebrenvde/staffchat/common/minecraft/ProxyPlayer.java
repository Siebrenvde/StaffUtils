package dev.siebrenvde.staffchat.common.minecraft;

import dev.siebrenvde.staffchat.common.StaffChat;

public interface ProxyPlayer extends Player {

    /**
     * {@return a new ProxyPlayer instance}
     * @param player the player
     * @param <P> the server software's player class
     */
    static <P> ProxyPlayer of(P player) {
        return (ProxyPlayer) StaffChat.getPlatform().getCommandSender(player);
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
