package dev.siebrenvde.staffchat.common.minecraft.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.common.StaffChat;
import dev.siebrenvde.staffchat.common.messages.Messages;
import dev.siebrenvde.staffchat.common.minecraft.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffChatCommand extends BaseCommand {

    public static final List<UUID> ENABLED_PLAYERS = new ArrayList<>();

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
                if(
                    platform.getServerType() == ServerPlatform.ServerType.VELOCITY
                    && CommandSender.of(ctx.getSource()) instanceof ProxyPlayer player
                    && player.getProtocolVersion() >= 760
                    /* Cannot deny signed messages on Velocity with 1.19.1+ clients */
                ) {
                    player.sendMessage(Component.text("Toggling StaffChat is not supported by your client", NamedTextColor.RED));
                    StaffChat.LOGGER.warn("Toggling StaffChat is not supported when using a 1.19.1+ client with Velocity");
                    return 1;
                }
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
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Only players can toggle StaffChat", NamedTextColor.RED));
            return;
        }
        UUID uuid = player.getUniqueId();
        if(!ENABLED_PLAYERS.contains(uuid)) {
            ENABLED_PLAYERS.add(uuid);
            player.sendMessage(
                Component.text("Enabled StaffChat", NamedTextColor.GREEN)
                    .appendNewline()
                    .append(Component.text("Any message you send will be sent to the staff chat"))
            );
        } else {
            ENABLED_PLAYERS.remove(uuid);
            player.sendMessage(Component.text("Disabled StaffChat", NamedTextColor.RED));
        }
    }

    public static void executeSendMessage(CommandSender sender, String message) {
        StaffChat.getPlatform().broadcast(
            Messages.staffChat().serverFromServer(sender, message),
            "staffchat.see"
        );
        StaffChat.getAddon().sendMessage(Messages.staffChat().discordFromServer(sender, message));
    }

}
