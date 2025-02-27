package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandSource;
import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class VelocityCommandManager implements BrigadierCommandManager<CommandSource> {

    private final CommandManager manager;

    public VelocityCommandManager(CommandManager manager) {
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

}
