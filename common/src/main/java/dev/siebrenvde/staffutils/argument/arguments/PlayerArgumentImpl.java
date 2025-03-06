package dev.siebrenvde.staffutils.argument.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.command.argument.arguments.PlayerArgument;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@NullMarked
public class PlayerArgumentImpl implements PlayerArgument {

    @Override
    public ArgumentType<?> getType() {
        return StringArgumentType.word();
    }

    @Override
    public <C> CompletableFuture<Suggestions> listSuggestions(CommandContext<C> ctx, SuggestionsBuilder builder) {
        List<Player> players = CommandSender.of(ctx.getSource()) instanceof ProxyPlayer player && !allowGlobal()
            ? player.getServer().getPlayers()
            : StaffUtils.getServer().getPlayers();

        players.stream()
            .map(Player::getName)
            .filter(name -> name.toLowerCase().startsWith(builder.getRemainingLowerCase()))
            .forEach(builder::suggest);

        return builder.buildFuture();
    }

    @Override
    public <C> @Nullable Player resolve(CommandContext<C> ctx, String name) throws CommandSyntaxException {
        String playerName = StringArgumentType.getString(ctx, name);

        Optional<Player> player = CommandSender.of(ctx.getSource()) instanceof ProxyPlayer proxyPlayer && !allowGlobal()
            ? proxyPlayer.getServer().getPlayers().stream()
                .filter(p -> p.getName().equalsIgnoreCase(playerName))
                .findFirst()
            : Player.byName(playerName);

        return player.orElseGet(() -> {
            CommandSender.of(ctx.getSource()).sendMessage(
                Messages.commands().playerNotFound(playerName)
            );
            return null;
        });
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean allowGlobal() {
        return Config.config().allowGlobalPlayerCommands.getRealValue();
    }

}
