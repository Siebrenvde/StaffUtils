package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.spigot.SpigotCommandSender;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperCommandSender extends SpigotCommandSender {

    public PaperCommandSender(CommandSender sender) {
        super(sender);
    }

    @Override
    public void sendMessage(Component message) {
        sender.sendMessage(message);
    }

}
