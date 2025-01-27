package dev.siebrenvde.staffutils.velocity;

import dev.siebrenvde.staffutils.api.player.CommonPlayer;
import dev.siebrenvde.staffutils.api.server.CommonServer;

import java.util.List;
import java.util.stream.Collectors;

public class VelocityGlobalServer implements CommonServer {

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return StaffUtilsVelocity.getProxy().getAllPlayers().stream()
            .map(VelocityPlayer::new)
            .collect(Collectors.toList());
    }

}
