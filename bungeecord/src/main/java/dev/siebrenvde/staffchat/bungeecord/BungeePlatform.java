package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.ServerPlatform;
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
        ProxiedPlayer player = StaffChatBungee.getInstance().getProxy().getPlayer(name);
        return player != null
            ? Optional.of(new BungeePlayer(player))
            : Optional.empty();
    }

}
