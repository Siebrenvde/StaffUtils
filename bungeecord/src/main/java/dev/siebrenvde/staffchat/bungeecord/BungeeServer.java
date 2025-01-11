package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.server.CommonServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.List;
import java.util.stream.Collectors;

public class BungeeServer implements CommonServer {

    private final ServerInfo serverInfo;

    public BungeeServer(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public String getName() {
        return serverInfo.getName();
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return serverInfo.getPlayers().stream()
            .map(BungeePlayer::new)
            .collect(Collectors.toList());
    }

}
