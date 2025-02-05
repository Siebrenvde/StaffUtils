package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;

public class ReportCommand extends BaseCommand {

    public ReportCommand() {
        super(Config.COMMANDS.report, null);
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
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
            ).build();
    }

    @Override
    public void simple(CommandSender sender, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Messages.report().usage());
            return;
        }
        executeReport(sender, args[0], String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
    }

    @Override
    public List<String> suggestions(CommandSender sender, String[] args) {
        if(args.length != 1) return List.of();
        return playerSuggestions(sender, args[0].toLowerCase());
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
        return Config.CONFIG.report.allowGlobal.getRealValue();
    }

}
