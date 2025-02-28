package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@NullMarked
public class PaperCommandManager implements BrigadierCommandManager<CommandSourceStack> {

    private final Commands commands;

    public PaperCommandManager(Commands commands) {
        this.commands = commands;
    }

    @Override
    public void register(BaseCommand command) {
        commands.register(
            command.brigadier(this),
            command.getDescription(),
            List.of(command.getAliases())
        );
    }

}
