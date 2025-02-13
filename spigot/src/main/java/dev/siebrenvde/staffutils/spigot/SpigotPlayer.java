package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

import java.util.UUID;

@NullMarked
public class SpigotPlayer extends SpigotCommandSender implements Player {

    private final org.bukkit.entity.Player player;

    public SpigotPlayer(org.bukkit.entity.Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public Component getDisplayName() {
        return PaperCompat.displayName(player);
    }

}
