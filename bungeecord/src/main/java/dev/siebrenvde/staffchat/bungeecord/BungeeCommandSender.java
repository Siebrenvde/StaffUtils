package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
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
    public String getDisplayName() {
        return getName();
    }

    @Override
    public void sendMessage(Component message) {
        StaffChatBungee.adventure().sender(sender).sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

}
