package dev.siebrenvde.staffutils.api.command.arguments;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.CommandSender;
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
public class PlayerArgument {

    public static StringArgumentType player() {
        return StringArgumentType.word();
    }

    public static <C> CompletableFuture<Suggestions> suggestPlayers(CommandContext<C> ctx, SuggestionsBuilder builder) {
        List<Player> players = CommandSender.of(ctx.getSource()) instanceof ProxyPlayer player && !allowGlobal()
            ? player.getServer().getPlayers()
            : StaffUtils.getServer().getPlayers();

        players.stream()
            .map(Player::getName)
            .filter(name -> name.toLowerCase().startsWith(builder.getRemainingLowerCase()))
            .forEach(builder::suggest);

        return builder.buildFuture();
    }

    public static <C> @Nullable Player resolvePlayer(CommandContext<C> ctx, String name) {
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
    private static boolean allowGlobal() {
        return Config.config().allowGlobalPlayerCommands.getRealValue();
    }

}
