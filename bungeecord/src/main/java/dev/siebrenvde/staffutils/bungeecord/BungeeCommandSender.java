package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.CommonCommandSender;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.CommandSender;

public class BungeeCommandSender implements CommonCommandSender {

    private final CommandSender sender;

    public BungeeCommandSender(CommandSender sender) {
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
