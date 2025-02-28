package dev.siebrenvde.staffutils.api.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import org.jspecify.annotations.NullMarked;

/**
 * A command manager for Brigadier commands
 * @param <C> The server software's command sender
 */
@NullMarked
public interface BrigadierCommandManager<C> extends CommandManager {

    /**
     * {@return a new LiteralArgumentBuilder for the provided name}
     * @param name the name of the literal
     */
    default LiteralArgumentBuilder<C> literal(String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    /**
     * {@return a new RequiredArgumentBuilder for the provided name and type}
     * @param name the name of the argument
     * @param type the type of the argument
     * @param <T> the type of the ArgumentType
     */
    default <T> RequiredArgumentBuilder<C, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

}
