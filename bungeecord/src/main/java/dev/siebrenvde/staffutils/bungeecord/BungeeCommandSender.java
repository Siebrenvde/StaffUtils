package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.kyori.adventure.audience.Audience;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BungeeCommandSender implements CommandSender {

    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeCommandSender(net.md_5.bungee.api.CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public Audience audience() {
        return StaffUtilsBungee.adventure().sender(sender);
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
