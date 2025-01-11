package dev.siebrenvde.staffchat.api.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

/**
 * A command manager for Brigadier commands
 * @param <C> The server software's command sender
 */
public interface BrigadierCommandManager<C> extends CommandManager {

    @Override
    void register(BaseCommand command);

    /**
     * {@return a new LiteralArgumentBuilder for the provided name}
     * @param name the name of the literal
     */
    LiteralArgumentBuilder<C> literal(String name);

    /**
     * {@return a new RequiredArgumentBuilder for the provided name and type}
     * @param name the name of the argument
     * @param type the type of the argument
     * @param <T> the type of the ArgumentType
     */
    <T> RequiredArgumentBuilder<C, T> argument(String name, ArgumentType<T> type);

}
