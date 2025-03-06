package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.api.ServerType;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.paper.command.PaperCommandSender;
import dev.siebrenvde.staffutils.spigot.SpigotPlatform;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperPlatform extends SpigotPlatform {

    @Override
    public ServerType getServerType() {
        return ServerType.PAPER;
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public <C> CommandSender getCommandSender(C source) {
        org.bukkit.command.CommandSender sender = ((CommandSourceStack) source).getSender();
        if(sender instanceof org.bukkit.entity.Player player) return getPlayer(player);
        return new PaperCommandSender(sender);
    }

    @Override
    public <P> Player getPlayer(P player) {
        return new PaperPlayer((org.bukkit.entity.Player) player);
    }

}
