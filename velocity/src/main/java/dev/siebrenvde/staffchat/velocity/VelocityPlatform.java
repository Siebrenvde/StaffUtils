package dev.siebrenvde.staffchat.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.ServerPlatform;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;

import java.util.Optional;

public class VelocityPlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return ServerType.VELOCITY;
    }

    @Override
    public <C> CommonCommandSender getCommandSender(C sender) {
        if(sender instanceof Player player) return getPlayer(player);
        return new VelocityCommandSender((CommandSource) sender);
    }

    @Override
    public <P> CommonPlayer getPlayer(P player) {
        return new VelocityPlayer((Player) player);
    }

    @Override
    public Optional<CommonPlayer> getPlayerByName(String name) {
        return StaffChatVelocity.getProxy().getPlayer(name).map(VelocityPlayer::new);
    }

}
