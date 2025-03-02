package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class VelocityCommandManager implements CommandManager<CommandSource> {

    private final com.velocitypowered.api.command.CommandManager manager;

    public VelocityCommandManager(com.velocitypowered.api.command.CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void register(BaseCommand<CommandSource> command) {
        manager.register(
            manager.metaBuilder(command.getName())
                .aliases(command.getAliases())
                .plugin(StaffUtilsVelocity.getInstance())
                .build(),
            new BrigadierCommand(command.builder())
        );
    }

}
