package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.command.CommonCommandSender;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.api.player.CommonPlayer;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import dev.siebrenvde.staffutils.paper.StaffUtilsPaper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class SpigotPlatform implements ServerPlatform {

    @Override
    public ServerType getServerType() {
        return PaperCompat.isPaper()
            ? ServerType.PAPER
            : ServerType.SPIGOT;
    }

    @Override
    public <C> CommonCommandSender getCommandSender(C sender) {
        if(PaperCompat.hasBrigadier()) sender = StaffUtilsPaper.senderFromSourceStack(sender);
        if(sender instanceof Player player) return getPlayer(player);
        return new SpigotCommandSender((CommandSender) sender);
    }

    @Override
    public <P> CommonPlayer getPlayer(P player) {
        return new SpigotPlayer((Player) player);
    }

    @Override
    public Optional<CommonPlayer> getPlayerByName(String name) {
        Player player = Bukkit.getPlayerExact(name);
        return player != null
            ? Optional.of(new SpigotPlayer(player))
            : Optional.empty();
    }

}
