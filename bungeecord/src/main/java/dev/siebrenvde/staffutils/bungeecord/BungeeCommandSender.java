package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BungeeCommandSender implements CommandSender {

    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeCommandSender(net.md_5.bungee.api.CommandSender sender) {
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
        StaffUtilsBungee.adventure().sender(sender).sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

}
