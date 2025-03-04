package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.ServerType;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.api.player.Player;
import org.bukkit.Bukkit;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@NullMarked
public class SpigotPlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return ServerType.SPIGOT;
    }

    @Override
    public <C> CommandSender getCommandSender(C sender) {
        if(sender instanceof org.bukkit.entity.Player player) return getPlayer(player);
        return new SpigotCommandSender((org.bukkit.command.CommandSender) sender);
    }

    @Override
    public <P> Player getPlayer(P player) {
        return new SpigotPlayer((org.bukkit.entity.Player) player);
    }

    @Override
    public Optional<Player> getPlayerByName(String name) {
        org.bukkit.entity.Player player = Bukkit.getPlayerExact(name);
        return player != null
            ? Optional.of(getPlayer(player))
            : Optional.empty();
    }

}
