package dev.siebrenvde.staffutils.paper;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
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

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> literal(String name) {
        return Commands.literal(name);
    }

    @Override
    public <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(String name, ArgumentType<T> type) {
        return Commands.argument(name, type);
    }

}
