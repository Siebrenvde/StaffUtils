package dev.siebrenvde.staffchat.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.config.CommandConfig;
import dev.siebrenvde.staffchat.messages.Messages;
import dev.siebrenvde.staffchat.api.command.BaseCommand;
import dev.siebrenvde.staffchat.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffchat.api.command.CommandSender;
import dev.siebrenvde.staffchat.api.player.Player;
import dev.siebrenvde.staffchat.util.Permissions;
import dev.siebrenvde.staffchat.util.SignedMessageCompat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffChatCommand extends BaseCommand {

    public static final List<UUID> ENABLED_PLAYERS = new ArrayList<>();

    public StaffChatCommand(CommandConfig.StaffChat config) {
        super(
            config.name,
            config.aliases,
            config.description,
            Permissions.COMMAND_STAFFCHAT
        );
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
        return manager.literal(getName())
            .requires(sender -> CommandSender.of(sender).hasPermission(getRootPermission()))
            .executes(ctx -> {
                executeToggle(CommandSender.of(ctx.getSource()));
                return 1;
            })
            .then(manager.argument("message", StringArgumentType.greedyString())
                .executes(ctx -> {
                    executeSendMessage(
                        CommandSender.of(ctx.getSource()),
                        StringArgumentType.getString(ctx, "message")
                    );
                    return 1;
                })
            )
            .build();
    }

    @Override
    public void simple(CommandSender sender, String[] args) {
        if(!checkPermission(sender, getRootPermission())) return;
        if(args.length == 0) executeToggle(sender);
        else executeSendMessage(sender, String.join(" ", args));
    }

    private void executeToggle(CommandSender sender) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Only players can toggle StaffChat", NamedTextColor.RED));
            return;
        }
        if(!SignedMessageCompat.isSupported(player)) return;
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
            Permissions.RECEIVE_STAFFCHAT
        );
        StaffChat.getAddon().sendMessage(Messages.staffChat().discordFromServer(sender, message));
    }

}
