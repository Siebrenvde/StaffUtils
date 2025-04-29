package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.paper.command.PaperCommandSender;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperPlayer extends PaperCommandSender implements Player {

    public PaperPlayer(org.bukkit.entity.Player player) {
        super(player);
    }

}
