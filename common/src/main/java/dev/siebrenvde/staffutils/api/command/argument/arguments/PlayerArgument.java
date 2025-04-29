package dev.siebrenvde.staffutils.api.command.argument.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.siebrenvde.staffutils.api.command.argument.CommandArgumentType;
import dev.siebrenvde.staffutils.api.player.Player;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static dev.siebrenvde.staffutils.api.command.argument.CommandArguments.arguments;

@NullMarked
public interface PlayerArgument extends CommandArgumentType<Player> {

    static ArgumentType<?> player() {
        return arguments().playerArgument().getType();
    }

    static <C> CompletableFuture<Suggestions> suggestPlayers(CommandContext<C> ctx, SuggestionsBuilder builder) {
        return arguments().playerArgument().listSuggestions(ctx, builder);
    }

    static <C> @Nullable Player resolvePlayer(CommandContext<C> ctx, String name) throws CommandSyntaxException {
        return arguments().playerArgument().resolve(ctx, name);
    }

}
