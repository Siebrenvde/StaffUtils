package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;
import net.md_5.bungee.api.config.ServerInfo;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.stream.Collectors;

@NullMarked
public class BungeeServer implements Server {

    private final ServerInfo serverInfo;

    public BungeeServer(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public String getName() {
        return serverInfo.getName();
    }

    @Override
    public List<Player> getPlayers() {
        return serverInfo.getPlayers().stream()
            .map(BungeePlayer::new)
            .collect(Collectors.toList());
    }

}
