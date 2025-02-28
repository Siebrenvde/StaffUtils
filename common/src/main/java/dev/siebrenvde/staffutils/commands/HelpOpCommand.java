package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;

import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;

@NullMarked
public class HelpOpCommand extends BaseCommand {

    public HelpOpCommand() {
        super(Config.commands().helpOp, null);
    }

    @Override
    public <C> LiteralArgumentBuilder<C> brigadier(CommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.argument("message", StringArgumentType.greedyString())
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
