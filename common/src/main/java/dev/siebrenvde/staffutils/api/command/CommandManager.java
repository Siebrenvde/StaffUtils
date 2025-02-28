package dev.siebrenvde.staffutils.api.command;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import org.jspecify.annotations.NullMarked;

import java.util.Arrays;

/**
 * A command manager for Brigadier commands
 * @param <C> The server software's command sender
 */
@NullMarked
public interface CommandManager<C> {

    /**
     * Registers all commands in the provided array
     * @param commands an array of commands to register
     */
    default void registerAll(BaseCommand... commands) {
        Arrays.stream(commands).forEach(command -> {
            if(command.isEnabled()) register(command);
        });
    }

    /**
     * Registers the provided command
     * @param command the command to register
     */
    void register(BaseCommand command);

    default Message message(String message) {
        return new LiteralMessage(message);
    }

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
