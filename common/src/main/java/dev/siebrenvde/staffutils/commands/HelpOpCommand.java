package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.CommonCommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;

import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;

public class HelpOpCommand extends BaseCommand {

    public HelpOpCommand() {
        super(Config.COMMANDS.helpOp, null);
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.argument("message", StringArgumentType.greedyString())
                .executes(withSender((ctx, sender) -> {
                    executeHelpOp(
                        sender,
                        StringArgumentType.getString(ctx, "message")
                    );
                }))
            )
            .build();
    }

    @Override
    public void simple(CommonCommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Messages.helpOp().usage());
            return;
        }
        executeHelpOp(sender, String.join(" ", args));
    }

    private void executeHelpOp(CommonCommandSender sender, String message) {
        sender.sendMessage(Messages.helpOp().success());
        StaffUtils.getServer().broadcast(
            Messages.helpOp().serverFromServer(sender, message),
            Permissions.RECEIVE_HELPOP
        );
        StaffUtils.getSpicord().sendMessage(Messages.helpOp().discordFromServer(sender, message));
    }

}
