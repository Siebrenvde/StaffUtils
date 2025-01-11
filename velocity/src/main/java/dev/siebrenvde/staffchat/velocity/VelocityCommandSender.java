package dev.siebrenvde.staffchat.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import net.kyori.adventure.text.Component;

public class VelocityCommandSender implements CommonCommandSender {

    private final CommandSource source;

    public VelocityCommandSender(CommandSource source) {
        this.source = source;
    }

    @Override
    public String getName() {
        if(source instanceof ConsoleCommandSource) return "Console";
        return "CommandSource";
    }

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public void sendMessage(Component message) {
        source.sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return source.hasPermission(permission);
    }

}