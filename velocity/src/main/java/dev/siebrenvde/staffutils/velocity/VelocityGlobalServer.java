package dev.siebrenvde.staffutils.velocity;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.server.Server;
import net.kyori.adventure.audience.Audience;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.stream.Collectors;

@NullMarked
public class VelocityGlobalServer implements Server {

    @Override
    public Iterable<? extends Audience> audiences() {
        return List.of(StaffUtilsVelocity.getProxy());
    }

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public List<Player> getPlayers() {
        return StaffUtilsVelocity.getProxy().getAllPlayers().stream()
            .map(VelocityPlayer::new)
            .collect(Collectors.toList());
    }

}
