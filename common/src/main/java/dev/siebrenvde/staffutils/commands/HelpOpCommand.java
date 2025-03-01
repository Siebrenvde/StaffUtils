package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class HelpOpCommand<C> extends BaseCommand<C> {

    public HelpOpCommand() {
        super(Config.commands().helpOp, null);
    }

    @Override
    public LiteralArgumentBuilder<C> builder() {
        return literal(getName())
            .then(argument("message", StringArgumentType.greedyString())
                .executes(withSender((ctx, sender) -> {
                    executeHelpOp(
                        sender,
                        StringArgumentType.getString(ctx, "message")
                    );
                }))
            );
    }

    private void executeHelpOp(CommandSender sender, String message) {
        sender.sendMessage(Messages.helpOp().success());
        StaffUtils.getServer().broadcast(
            Messages.helpOp().serverFromServer(sender, message),
            Permissions.RECEIVE_HELPOP
        );
        StaffUtils.getSpicord().sendMessage(Messages.helpOp().discordFromServer(sender, message));
    }

}
