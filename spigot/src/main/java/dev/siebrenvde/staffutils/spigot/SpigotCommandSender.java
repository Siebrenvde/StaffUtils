package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.kyori.adventure.text.Component;

public class SpigotCommandSender implements CommandSender {

    private final org.bukkit.command.CommandSender sender;

    public SpigotCommandSender(org.bukkit.command.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public Component getDisplayName() {
        return Component.text(getName());
    }

    @Override
    public void sendMessage(Component message) {
        StaffUtilsSpigot.adventure().sender(sender).sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

}
