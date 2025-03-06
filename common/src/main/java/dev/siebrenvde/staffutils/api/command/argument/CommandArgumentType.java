package dev.siebrenvde.staffutils.api.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public interface CommandArgumentType<T> {

    ArgumentType<?> getType();

    <C> CompletableFuture<Suggestions> listSuggestions(CommandContext<C> ctx, SuggestionsBuilder builder);

    <C> @Nullable T resolve(CommandContext<C> ctx, String name) throws CommandSyntaxException;

}
