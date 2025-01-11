package dev.siebrenvde.staffchat.velocity;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandSource;
import dev.siebrenvde.staffchat.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffchat.api.command.BaseCommand;

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
                .plugin(StaffChatVelocity.getInstance())
                .build(),
            new BrigadierCommand(command.brigadier(this))
        );
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> literal(String name) {
        return BrigadierCommand.literalArgumentBuilder(name);
    }

    @Override
    public <T> RequiredArgumentBuilder<CommandSource, T> argument(String name, ArgumentType<T> type) {
        return BrigadierCommand.requiredArgumentBuilder(name, type);
    }

}
