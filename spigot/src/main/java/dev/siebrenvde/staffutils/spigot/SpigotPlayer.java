package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.player.CommonPlayer;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotPlayer extends SpigotCommandSender implements CommonPlayer {

    private final Player player;

    public SpigotPlayer(Player player) {
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
