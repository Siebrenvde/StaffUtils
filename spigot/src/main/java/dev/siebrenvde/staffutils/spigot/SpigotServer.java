package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.stream.Collectors;

@NullMarked
public class SpigotServer implements Server {

    @Override
    public Iterable<? extends Audience> audiences() {
        return List.of(StaffUtilsSpigot.adventure().all());
    }

    @Override
    public String getName() {
        return "server";
    }

    @Override
    public List<Player> getPlayers() {
        return Bukkit.getOnlinePlayers().stream()
            .map(Player::of)
            .collect(Collectors.toList());
    }

}
