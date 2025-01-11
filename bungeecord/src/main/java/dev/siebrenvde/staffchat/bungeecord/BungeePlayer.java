package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.player.ProxyPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class BungeePlayer extends BungeeCommandSender implements ProxyPlayer {

    private final ProxiedPlayer player;

    public BungeePlayer(ProxiedPlayer player) {
        super(player);
        this.player = player;
    }

    @Override
    public BungeeServer getServer() {
        return new BungeeServer(player.getServer().getInfo());
    }

    @Override
    public int getProtocolVersion() {
        return player.getPendingConnection().getVersion();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

}
