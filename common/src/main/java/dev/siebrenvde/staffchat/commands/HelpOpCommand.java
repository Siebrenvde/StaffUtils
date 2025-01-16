package dev.siebrenvde.staffchat.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.command.BaseCommand;
import dev.siebrenvde.staffchat.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.config.Config;
import dev.siebrenvde.staffchat.messages.Messages;
import dev.siebrenvde.staffchat.util.Permissions;

public class HelpOpCommand extends BaseCommand {

    public HelpOpCommand() {
        super(Config.COMMANDS.helpOp, null);
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.argument("message", StringArgumentType.greedyString())
                .executes(ctx -> {
                    executeHelpOp(
                        CommonCommandSender.of(ctx.getSource()),
                        StringArgumentType.getString(ctx, "message")
                    );
                    return 1;
                })
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
        StaffChat.getServer().broadcast(
            Messages.helpOp().serverFromServer(sender, message),
            Permissions.RECEIVE_HELPOP
        );
        StaffChat.getSpicord().sendMessage(Messages.helpOp().discordFromServer(sender, message));
    }

}
