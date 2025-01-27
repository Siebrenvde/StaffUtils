package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.player.CommonPlayer;
import dev.siebrenvde.staffutils.api.server.CommonServer;

import java.util.List;
import java.util.stream.Collectors;

public class BungeeGlobalServer implements CommonServer {

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return StaffUtilsBungee.getInstance().getProxy().getPlayers().stream()
            .map(BungeePlayer::new)
            .collect(Collectors.toList());
    }

}
