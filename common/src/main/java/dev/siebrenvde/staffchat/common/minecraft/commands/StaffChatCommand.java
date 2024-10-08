package dev.siebrenvde.staffchat.common.minecraft.commands;

import dev.siebrenvde.staffchat.common.minecraft.CommandSender;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.description.Description;
import org.incendo.cloud.parser.standard.StringParser;

public class StaffChatCommand {

    public static <C> void register(CommandManager<C> manager, ServerPlatform platform) {
        manager.command(
            manager.commandBuilder("staffchat", Description.of("Chat with other staff members"), "sc", "schat", "staffc")
                .permission("staffchat.use")
                .optional("message", StringParser.greedyStringParser())
                .handler(ctx -> {
                    CommandSender sender = platform.getCommandSender(ctx.sender());
                    String message = ctx.getOrDefault("message", "");

                    if(message.isEmpty()) {
                        // TODO: Toggle
                        return;
                    }

                    // TODO: Send the message
                })
        );
    }

}
