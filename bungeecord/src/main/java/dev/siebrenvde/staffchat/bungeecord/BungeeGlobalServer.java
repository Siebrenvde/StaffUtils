package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.server.CommonServer;

import java.util.List;
import java.util.stream.Collectors;

public class BungeeGlobalServer implements CommonServer {

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return StaffChatBungee.getInstance().getProxy().getPlayers().stream()
            .map(BungeePlayer::new)
            .collect(Collectors.toList());
    }

}
