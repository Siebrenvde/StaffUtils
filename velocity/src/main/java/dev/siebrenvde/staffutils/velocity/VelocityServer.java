package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.proxy.ServerConnection;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VelocityServer extends VelocityGlobalServer implements Server {

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
    public List<Player> getPlayers() {
        return connection != null
            ? connection.getServer().getPlayersConnected().stream()
                .map(VelocityPlayer::new)
                .collect(Collectors.toList())
            : Collections.emptyList();
    }

}
