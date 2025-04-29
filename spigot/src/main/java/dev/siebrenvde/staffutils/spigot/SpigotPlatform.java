package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.ServerType;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.api.player.Player;
import org.jspecify.annotations.NullMarked;

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

}
