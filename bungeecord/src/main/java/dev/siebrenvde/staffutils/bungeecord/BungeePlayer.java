package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jspecify.annotations.NullMarked;

import java.util.UUID;

@NullMarked
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
