package dev.siebrenvde.staffchat.spigot;

import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class SpigotCommandSender implements CommonCommandSender {

    private final CommandSender sender;

    public SpigotCommandSender(CommandSender sender) {
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
        StaffChatSpigot.adventure().sender(sender).sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

}
