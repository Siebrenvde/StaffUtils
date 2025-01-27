package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Optional;

public class BungeePlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return ServerType.BUNGEECORD;
    }

    @Override
    public <C> CommandSender getCommandSender(C sender) {
        if(sender instanceof ProxiedPlayer player) return getPlayer(player);
        return new BungeeCommandSender((net.md_5.bungee.api.CommandSender) sender);
    }

    @Override
    public <P> Player getPlayer(P player) {
        return new BungeePlayer((ProxiedPlayer) player);
    }

    @Override
    public Optional<Player> getPlayerByName(String name) {
        ProxiedPlayer player = StaffUtilsBungee.getInstance().getProxy().getPlayer(name);
        return player != null
            ? Optional.of(new BungeePlayer(player))
            : Optional.empty();
    }

}
