package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.argument.arguments.PlayerArgument;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ReportCommand<C> extends BaseCommand<C> {

    public ReportCommand() {
        super(Config.commands().report, null);
    }

    @Override
    public LiteralArgumentBuilder<C> builder() {
        return literal(getName())
            .then(argument("player", PlayerArgument.player())
                .suggests(PlayerArgument::suggestPlayers)
                .then(argument("reason", StringArgumentType.greedyString())
                    .executes(withSender((ctx, sender) -> {
                        Player player = PlayerArgument.resolvePlayer(ctx, "player");
                        if (player == null) return;
                        String reason = StringArgumentType.getString(ctx, "reason");

                        sender.sendMessage(Messages.report().success(player));
                        StaffUtils.getServer().broadcast(
                            Messages.report().serverFromServer(sender, player, reason),
                            Permissions.RECEIVE_REPORT
                        );
                        StaffUtils.getSpicord().sendMessage(Messages.report().discordFromServer(sender, player, reason));
                    }))
                )
            );
    }

}
