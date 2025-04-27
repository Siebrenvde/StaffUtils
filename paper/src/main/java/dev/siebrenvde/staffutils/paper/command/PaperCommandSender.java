package dev.siebrenvde.staffutils.paper.command;

import dev.siebrenvde.staffutils.spigot.SpigotCommandSender;
import net.kyori.adventure.audience.Audience;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperCommandSender extends SpigotCommandSender {

    public PaperCommandSender(CommandSender sender) {
        super(sender);
    }

    @Override
    public Audience audience() {
        return sender;
    }

}
