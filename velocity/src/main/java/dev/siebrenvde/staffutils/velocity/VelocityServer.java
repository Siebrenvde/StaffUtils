package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.proxy.ServerConnection;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;
import net.kyori.adventure.audience.Audience;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NullMarked
public class VelocityServer implements Server {

    @Nullable private final ServerConnection connection;

    public VelocityServer(@Nullable ServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public Iterable<? extends Audience> audiences() {
        if (connection == null) return List.of();
        return List.of(connection.getServer());
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
