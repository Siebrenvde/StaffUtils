package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.kyori.adventure.audience.Audience;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class VelocityCommandSender implements CommandSender {

    private final CommandSource source;

    public VelocityCommandSender(CommandSource source) {
        this.source = source;
    }

    @Override
    public Audience audience() {
        return source;
    }

    @Override
    public String getName() {
        if (source instanceof ConsoleCommandSource) return "Console";
        return "CommandSource";
    }

    @Override
    public boolean hasPermission(String permission) {
        return source.hasPermission(permission);
    }

}
