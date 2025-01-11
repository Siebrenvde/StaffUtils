package dev.siebrenvde.staffchat.velocity;

import com.velocitypowered.api.proxy.ServerConnection;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.server.CommonServer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VelocityServer extends VelocityGlobalServer implements CommonServer {

    private final ServerConnection connection;

    public VelocityServer(ServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getName() {
        return connection != null
            ? connection.getServerInfo().getName()
            : "unknown";
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return connection != null
            ? connection.getServer().getPlayersConnected().stream()
                .map(VelocityPlayer::new)
                .collect(Collectors.toList())
            : Collections.emptyList();
    }

}
