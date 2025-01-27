package dev.siebrenvde.staffutils.util;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.player.CommonPlayer;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class SignedMessageCompat {

    /**
     * The protocol version for 1.19.1
     */
    private static final int V1_19_1 = 760;

    /**
     * {@return whether signed messages can be edited or canceled}
     * @param player the player whose protocol version to check
     */
    public static boolean isSupported(CommonPlayer player) {
        if(!StaffUtils.getPlatform().isProxy()) return true;
        if(((ProxyPlayer) player).getProtocolVersion() < V1_19_1) return true;

        ServerPlatform.ServerType serverType = StaffUtils.getPlatform().getServerType();

        if(serverType == ServerPlatform.ServerType.VELOCITY) {
            try {
                Class.forName("io.github._4drian3d.signedvelocity.velocity.SignedVelocity");
                return true;
            } catch (ClassNotFoundException ignored) {}
        }

        TextComponent.Builder playerMessage = Component.text();
        playerMessage.color(NamedTextColor.RED);
        playerMessage.content("Toggling staff chat is not supported on this server");
        if(serverType == ServerPlatform.ServerType.VELOCITY) {
            playerMessage.appendNewline();
            playerMessage.append(Component.text("Contact your server administrator"));
        }
        player.sendMessage(playerMessage.build());

        StaffUtils.LOGGER.warn("Toggling staff chat is not supported when using a 1.19.1+ server through a proxy");
        if(serverType == ServerPlatform.ServerType.VELOCITY) {
            StaffUtils.LOGGER.warn("On Velocity you can fix this by installing SignedVelocity on the proxy and all servers");
            StaffUtils.LOGGER.warn("You can download SignedVelocity at https://modrinth.com/plugin/signedvelocity");
        }

        return false;
    }

}
