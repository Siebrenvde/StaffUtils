package dev.siebrenvde.staffchat.velocity;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.server.CommonServer;

import java.util.List;
import java.util.stream.Collectors;

public class VelocityGlobalServer implements CommonServer {

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return StaffChatVelocity.getProxy().getAllPlayers().stream()
            .map(VelocityPlayer::new)
            .collect(Collectors.toList());
    }

}
