package dev.siebrenvde.staffutils.util;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.ServerType;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import dev.siebrenvde.staffutils.messages.Messages;

public class SignedMessageCompat {

    /**
     * The protocol version for 1.19.1
     */
    private static final int V1_19_1 = 760;

    /**
     * {@return whether signed messages can be edited or canceled}
     * @param player the player whose protocol version to check
     */
    public static boolean isSupported(Player player) {
        if(!StaffUtils.getPlatform().isProxy()) return true;
        if(((ProxyPlayer) player).getProtocolVersion() < V1_19_1) return true;

        ServerType serverType = StaffUtils.getPlatform().getServerType();

        if(serverType == ServerType.VELOCITY) {
            try {
                Class.forName("io.github._4drian3d.signedvelocity.velocity.SignedVelocity");
                return true;
            } catch (ClassNotFoundException ignored) {}
        }

        player.sendMessage(Messages.staffChat().signedToggleFail());

        StaffUtils.LOGGER.warn("Toggling staff chat is not supported when using a 1.19.1+ server through a proxy");
        if(serverType == ServerType.VELOCITY) {
            StaffUtils.LOGGER.warn("On Velocity you can fix this by installing SignedVelocity on the proxy and all servers");
            StaffUtils.LOGGER.warn("You can download SignedVelocity at https://modrinth.com/plugin/signedvelocity");
        }

        return false;
    }

}
