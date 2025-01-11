package dev.siebrenvde.staffchat.spigot;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.server.CommonServer;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.stream.Collectors;

public class SpigotServer implements CommonServer {

    @Override
    public String getName() {
        return "server";
    }

    @Override
    public List<CommonPlayer> getPlayers() {
        return Bukkit.getOnlinePlayers().stream()
            .map(SpigotPlayer::new)
            .collect(Collectors.toList());
    }

}
