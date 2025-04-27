package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.proxy.Player;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class VelocityPlayer extends VelocityCommandSender implements ProxyPlayer {

    private final Player player;

    public VelocityPlayer(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getUsername();
    }

    @Override
    public VelocityServer getServer() {
        return new VelocityServer(player.getCurrentServer().orElse(null));
    }

    @Override
    public int getProtocolVersion() {
        return player.getProtocolVersion().getProtocol();
    }

}
