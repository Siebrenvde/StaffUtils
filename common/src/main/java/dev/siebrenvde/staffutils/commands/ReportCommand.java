package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;

import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;

@NullMarked
public class ReportCommand extends BaseCommand {

    public ReportCommand() {
        super(Config.commands().report, null);
    }

    @Override
    public <C> LiteralArgumentBuilder<C> brigadier(CommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.argument("player", StringArgumentType.word())
                .suggests((ctx, builder) -> {
                    playerSuggestions(
                        CommandSender.of(ctx.getSource()),
                        builder.getRemaining().toLowerCase()
                    ).forEach(builder::suggest);
                    return builder.buildFuture();
                })
                .then(manager.argument("reason", StringArgumentType.greedyString())
                    .executes(withSender((ctx, sender) -> {
                        executeReport(
                            sender,
                            StringArgumentType.getString(ctx, "player"),
                            StringArgumentType.getString(ctx, "reason")
                        );
                    }))
                )
            );
    }

    private void executeReport(CommandSender sender, String playerName, String reason) {
        Optional<Player> optionalPlayer = sender instanceof ProxyPlayer proxyPlayer && !allowGlobal()
            ? proxyPlayer.getServer().getPlayers().stream()
                .filter(player -> player.getName().equalsIgnoreCase(playerName))
                .findFirst()
            : Player.byName(playerName);

        optionalPlayer.ifPresentOrElse(player -> {
            sender.sendMessage(Messages.report().success(player));
            StaffUtils.getServer().broadcast(
                Messages.report().serverFromServer(sender, player, reason),
                Permissions.RECEIVE_REPORT
            );
            StaffUtils.getSpicord().sendMessage(Messages.report().discordFromServer(sender, player, reason));
        }, () -> sender.sendMessage(Messages.report().playerNotFound(playerName)));
    }

    private List<String> playerSuggestions(CommandSender sender, String arg) {
        List<Player> players = sender instanceof ProxyPlayer player && !allowGlobal()
            ? player.getServer().getPlayers()
            : StaffUtils.getServer().getPlayers();
        return players.stream()
            .map(Player::getName)
            .filter(name -> name.toLowerCase().startsWith(arg))
            .toList();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean allowGlobal() {
        return Config.config().report.allowGlobal.getRealValue();
    }

}
