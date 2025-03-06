package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.paper.command.PaperCommandSender;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

import java.util.UUID;

@NullMarked
public class PaperPlayer extends PaperCommandSender implements Player {

    private final org.bukkit.entity.Player player;

    public PaperPlayer(org.bukkit.entity.Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public Component getDisplayName() {
        return player.displayName();
    }

}
