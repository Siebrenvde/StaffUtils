package dev.siebrenvde.staffchat.common.minecraft.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.common.minecraft.BrigadierCommandManager;
import dev.siebrenvde.staffchat.common.minecraft.CommandSender;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;

public class StaffChatCommand extends BaseCommand {

    public StaffChatCommand() {
        super(
            "staffchat",
            new String[]{"sc", "schat"},
            "Chat with other staff members",
            "staffchat.use"
        );
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager, ServerPlatform platform) {
        return manager.literal(getName())
            .requires(sender -> platform.getCommandSender(sender).hasPermission(getPermission()))
            .executes(ctx -> {
                executeToggle(platform.getCommandSender(ctx.getSource()));
                return 1;
            })
            .then(manager.argument("message", StringArgumentType.greedyString())
                .executes(ctx -> {
                    executeSendMessage(
                        platform.getCommandSender(ctx.getSource()),
                        StringArgumentType.getString(ctx, "message")
                    );
                    return 1;
                })
            )
            .build();
    }

    @Override
    public void simple(CommandSender sender, String[] args) {
        if(!checkPermission(sender)) return;
        if(args.length == 0) executeToggle(sender);
        else executeSendMessage(sender, String.join(" ", args));
    }

    private void executeToggle(CommandSender sender) {
        // TODO: Implement
    }

    private void executeSendMessage(CommandSender sender, String message) {
        // TODO: Implement
    }

}
