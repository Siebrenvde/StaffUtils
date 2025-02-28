package dev.siebrenvde.staffutils.velocity;

import com.mojang.brigadier.Message;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import org.jspecify.annotations.NullMarked;
import net.kyori.adventure.text.Component;

@NullMarked
public class VelocityCommandManager implements CommandManager<CommandSource> {

    private final com.velocitypowered.api.command.CommandManager manager;

    public VelocityCommandManager(com.velocitypowered.api.command.CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void register(BaseCommand command) {
        manager.register(
            manager.metaBuilder(command.getName())
                .aliases(command.getAliases())
                .plugin(StaffUtilsVelocity.getInstance())
                .build(),
            new BrigadierCommand(command.brigadier(this))
        );
    }

    @Override
    public Message message(String message) {
        return VelocityBrigadierMessage.tooltip(Component.text(message));
    }

}
