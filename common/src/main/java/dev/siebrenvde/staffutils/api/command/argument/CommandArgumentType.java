package dev.siebrenvde.staffutils.api.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@NullMarked
public interface CommandArgumentType<T extends @Nullable Object> {

    ArgumentType<?> getType();

    default <C> CompletableFuture<Suggestions> listSuggestions(CommandContext<C> ctx, SuggestionsBuilder builder) {
        return getType().listSuggestions(ctx, builder);
    }

    <C> @Nullable T resolve(CommandContext<C> ctx, String name) throws CommandSyntaxException;

}
