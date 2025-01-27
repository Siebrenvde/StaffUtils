package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.CommonCommandSender;
import dev.siebrenvde.staffutils.api.player.CommonPlayer;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Optional;

public class BungeePlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return ServerType.BUNGEECORD;
    }

    @Override
    public <C> CommonCommandSender getCommandSender(C sender) {
        if(sender instanceof ProxiedPlayer player) return getPlayer(player);
        return new BungeeCommandSender((CommandSender) sender);
    }

    @Override
    public <P> CommonPlayer getPlayer(P player) {
        return new BungeePlayer((ProxiedPlayer) player);
    }

    @Override
    public Optional<CommonPlayer> getPlayerByName(String name) {
        ProxiedPlayer player = StaffUtilsBungee.getInstance().getProxy().getPlayer(name);
        return player != null
            ? Optional.of(new BungeePlayer(player))
            : Optional.empty();
    }

}
