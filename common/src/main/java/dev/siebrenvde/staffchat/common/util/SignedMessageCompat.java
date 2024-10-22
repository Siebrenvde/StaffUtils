package dev.siebrenvde.staffchat.common.util;

import dev.siebrenvde.staffchat.common.StaffChat;
import dev.siebrenvde.staffchat.common.minecraft.Player;
import dev.siebrenvde.staffchat.common.minecraft.ProxyPlayer;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
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
    public static boolean isSupported(Player player) {
        if(!StaffChat.getPlatform().isProxy()) return true;
        if(((ProxyPlayer) player).getProtocolVersion() < V1_19_1) return true;

        ServerPlatform.ServerType serverType = StaffChat.getPlatform().getServerType();

        if(serverType == ServerPlatform.ServerType.VELOCITY) {
            try {
                Class.forName("io.github._4drian3d.signedvelocity.velocity.SignedVelocity");
                return true;
            } catch (ClassNotFoundException ignored) {}
        }

        TextComponent.Builder playerMessage = Component.text();
        playerMessage.color(NamedTextColor.RED);
        playerMessage.content("Toggling StaffChat is not supported on this server");
        if(serverType == ServerPlatform.ServerType.VELOCITY) {
            playerMessage.appendNewline();
            playerMessage.append(Component.text("Contact your server administrator"));
        }
        player.sendMessage(playerMessage.build());

        StaffChat.LOGGER.warn("Toggling StaffChat is not supported when using a 1.19.1+ server through a proxy");
        if(serverType == ServerPlatform.ServerType.VELOCITY) {
            StaffChat.LOGGER.warn("On Velocity you can fix this by installing SignedVelocity on the proxy and all servers");
            StaffChat.LOGGER.warn("You can download SignedVelocity at https://modrinth.com/plugin/signedvelocity");
        }

        return false;
    }

}
