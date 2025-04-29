package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.player.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SpigotPlayer extends SpigotCommandSender implements Player {

    public SpigotPlayer(org.bukkit.entity.Player player) {
        super(player);
    }

}
