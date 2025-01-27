package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;

import java.util.List;
import java.util.stream.Collectors;

public class BungeeGlobalServer implements Server {

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

}
