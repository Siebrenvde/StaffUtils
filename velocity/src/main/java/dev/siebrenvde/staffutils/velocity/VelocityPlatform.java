package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.siebrenvde.staffutils.api.command.CommonCommandSender;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.api.player.CommonPlayer;

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
        return StaffUtilsVelocity.getProxy().getPlayer(name).map(VelocityPlayer::new);
    }

}
