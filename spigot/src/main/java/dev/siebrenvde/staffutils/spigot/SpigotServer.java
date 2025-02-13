package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;
import org.bukkit.Bukkit;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.stream.Collectors;

@NullMarked
public class SpigotServer implements Server {

    @Override
    public String getName() {
        return "server";
    }

    @Override
    public List<Player> getPlayers() {
        return Bukkit.getOnlinePlayers().stream()
            .map(SpigotPlayer::new)
            .collect(Collectors.toList());
    }

}
