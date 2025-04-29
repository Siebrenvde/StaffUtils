package dev.siebrenvde.staffutils.velocity;

import com.mojang.brigadier.Message;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.siebrenvde.staffutils.api.ServerType;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.api.player.Player;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class VelocityPlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return ServerType.VELOCITY;
    }

    @Override
    public <C> CommandSender getCommandSender(C sender) {
        if(sender instanceof com.velocitypowered.api.proxy.Player player) return getPlayer(player);
        return new VelocityCommandSender((CommandSource) sender);
    }

    @Override
    public <P> Player getPlayer(P player) {
        return new VelocityPlayer((com.velocitypowered.api.proxy.Player) player);
    }

    @Override
    public Message message(String message) {
        return VelocityBrigadierMessage.tooltip(Component.text(message));
    }

}
