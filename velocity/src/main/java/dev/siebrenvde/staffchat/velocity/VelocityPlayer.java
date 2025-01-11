package dev.siebrenvde.staffchat.velocity;

import com.velocitypowered.api.proxy.Player;
import dev.siebrenvde.staffchat.api.player.ProxyPlayer;

import java.util.UUID;

public class VelocityPlayer extends VelocityCommandSender implements ProxyPlayer {

    private final Player player;

    public VelocityPlayer(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
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