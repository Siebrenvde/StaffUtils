package dev.siebrenvde.staffutils.api.player;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.server.CommonServer;

public interface ProxyPlayer extends CommonPlayer {

    /**
     * {@return a new ProxyPlayer instance}
     * @param player the player
     * @param <P> the server software's player class
     */
    static <P> ProxyPlayer of(P player) {
        return (ProxyPlayer) StaffUtils.getPlatform().getPlayer(player);
    }

    /**
     * {@return the server the player is connected to}
     */
    CommonServer getServer();

    /**
     * {@return the protocol version of the player}
     */
    int getProtocolVersion();

}
