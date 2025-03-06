package dev.siebrenvde.staffutils.api.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.siebrenvde.staffutils.config.CommandConfig;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Predicate;

/**
 * The base command
 * @param <C> the server software's command sender
 */
@NullMarked
public abstract class BaseCommand<C> {

    private final boolean enabled;
    private final String name;
    private final String[] aliases;
    private final String description;
    @Nullable private final String rootPermission;

    /**
     * Creates a new command with the provided parameters
     * @param name the name of the command
     * @param aliases the aliases of the command
     * @param description the description of the command
     * @param rootPermission the root permission of the command
     */
    protected BaseCommand(String name, String @Nullable[] aliases, @Nullable String description, @Nullable String rootPermission) {
        enabled = true;
        this.name = name;
        this.aliases = aliases != null ? aliases : new String[0];
        this.description = description != null ? description : "";
        this.rootPermission = rootPermission;
    }

    /**
     * Creates a new command from a config file
     * @param config the command config
     * @param rootPermission the root permission of the command
     */
    protected BaseCommand(CommandConfig.Command config, @Nullable String rootPermission) {
        this.enabled = config.enabled.getRealValue();
        this.name = config.name.getRealValue();
        this.aliases = config.aliases.getRealValue().toArray(new String[0]);
        this.description = config.description.getRealValue();
        this.rootPermission = rootPermission;
    }

    /**
     * The implementation of the command
     * @return a new LiteralArgumentBuilder
     */
    public abstract LiteralArgumentBuilder<C> builder();

    /**
     * {@return whether the command is enabled}
     */
    public boolean isEnabled() { return enabled; }

    /**
     * {@return the name of the command}
     */
    public String getName() { return name; }

    /**
     * {@return the aliases of the command}
     */
    public String[] getAliases() { return aliases; }

    /**
     * {@return the description of the command}
     */
    public String getDescription() { return description; }

    /**
     * {@return the root permission of the command}
     */
    public @Nullable String getRootPermission() { return rootPermission; }

    protected Predicate<C> hasPermission(@Nullable String permission) {
        return source -> permission == null || CommandSender.of(source).hasPermission(permission);
    }

    protected Command<C> withSender(SenderCommand<C> command) {
        return ctx -> {
            command.run(ctx, CommandSender.of(ctx.getSource()));
            return Command.SINGLE_SUCCESS;
        };
    }

    @FunctionalInterface
    protected interface SenderCommand<C> {
        void run(CommandContext<C> ctx, CommandSender sender) throws CommandSyntaxException;
    }

    /**
     * {@return a new LiteralArgumentBuilder for the provided name}
     * @param name the name of the literal
     */
    protected LiteralArgumentBuilder<C> literal(String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    /**
     * {@return a new RequiredArgumentBuilder for the provided name and type}
     * @param name the name of the argument
     * @param type the type of the argument
     * @param <T> the type of the ArgumentType
     */
    protected <T> RequiredArgumentBuilder<C, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

}
