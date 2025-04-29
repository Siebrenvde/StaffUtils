package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;
import net.kyori.adventure.audience.Audience;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NullMarked
public class BungeeGlobalServer implements Server {

    @Override
    public Iterable<? extends Audience> audiences() {
        return List.of(StaffUtilsBungee.adventure().all());
    }

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public List<Player> getPlayers() {
        return StaffUtilsBungee.getInstance().getProxy().getPlayers().stream()
            .map(BungeePlayer::new)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        ProxiedPlayer player = StaffUtilsBungee.getInstance().getProxy().getPlayer(name);
        return Optional.ofNullable(player).map(BungeePlayer::new);
    }

}
