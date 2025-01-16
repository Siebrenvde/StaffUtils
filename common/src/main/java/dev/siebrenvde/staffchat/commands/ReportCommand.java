package dev.siebrenvde.staffchat.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.command.BaseCommand;
import dev.siebrenvde.staffchat.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.api.player.ProxyPlayer;
import dev.siebrenvde.staffchat.config.Config;
import dev.siebrenvde.staffchat.messages.Messages;
import dev.siebrenvde.staffchat.util.Permissions;
import eu.mcdb.util.ArrayUtils;

import java.util.List;

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
                        CommonCommandSender.of(ctx.getSource()),
                        builder.getRemaining().toLowerCase()
                    ).forEach(builder::suggest);
                    return builder.buildFuture();
                })
                .then(manager.argument("reason", StringArgumentType.greedyString())
                    .executes(ctx -> {
                        executeReport(
                            CommonCommandSender.of(ctx.getSource()),
                            StringArgumentType.getString(ctx, "player"),
                            StringArgumentType.getString(ctx, "reason")
                        );
                        return 1;
                    })
                )
            ).build();
    }

    @Override
    public void simple(CommonCommandSender sender, String[] args) {
        if(args.length < 2) {
            sender.sendMessage(Messages.report().usage());
            return;
        }
        executeReport(sender, args[0], String.join(" ", ArrayUtils.shift(args)));
    }

    @Override
    public List<String> suggestions(CommonCommandSender sender, String[] args) {
        if(args.length != 1) return List.of();
        return playerSuggestions(sender, args[0].toLowerCase());
    }

    private void executeReport(CommonCommandSender sender, String playerName, String reason) {
        CommonPlayer.byName(playerName).ifPresentOrElse(player -> {
            sender.sendMessage(Messages.report().success(player));
            StaffChat.getServer().broadcast(
                Messages.report().serverFromServer(sender, player, reason),
                Permissions.RECEIVE_REPORT
            );
            StaffChat.getSpicord().sendMessage(Messages.report().discordFromServer(sender, player, reason));
        }, () -> sender.sendMessage(Messages.report().playerNotFound(playerName)));
    }

    private List<String> playerSuggestions(CommonCommandSender sender, String arg) {
        // TODO: Proxy global players config option
        List<CommonPlayer> players = sender instanceof ProxyPlayer player
            ? player.getServer().getPlayers()
            : StaffChat.getServer().getPlayers();
        return players.stream()
            .map(CommonPlayer::getName)
            .filter(name -> name.toLowerCase().startsWith(arg))
            .toList();
    }

}
