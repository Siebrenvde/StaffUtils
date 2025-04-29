package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.kyori.adventure.audience.Audience;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SpigotCommandSender implements CommandSender {

    protected final org.bukkit.command.CommandSender sender;

    public SpigotCommandSender(org.bukkit.command.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public Audience audience() {
        return StaffUtilsSpigot.adventure().sender(sender);
    }

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

}
