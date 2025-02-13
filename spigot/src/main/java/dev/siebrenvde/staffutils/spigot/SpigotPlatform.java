package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.ServerType;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import dev.siebrenvde.staffutils.paper.StaffUtilsPaper;
import org.bukkit.Bukkit;

import java.util.Optional;

public class SpigotPlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return PaperCompat.isPaper()
            ? ServerType.PAPER
            : ServerType.SPIGOT;
    }

    @Override
    public <C> CommandSender getCommandSender(C sender) {
        if(PaperCompat.hasBrigadier()) sender = StaffUtilsPaper.senderFromSourceStack(sender);
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
            ? Optional.of(new SpigotPlayer(player))
            : Optional.empty();
    }

}
