package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.util.Permissions;
import dev.siebrenvde.staffutils.util.SignedMessageCompat;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NullMarked
public class StaffChatCommand<C> extends BaseCommand<C> {

    public static final List<UUID> ENABLED_PLAYERS = new ArrayList<>();

    public StaffChatCommand() {
        super(Config.commands().staffChat, Permissions.COMMAND_STAFFCHAT);
    }

    @Override
    public LiteralArgumentBuilder<C> builder() {
        return literal(getName())
            .requires(hasPermission(getRootPermission()))
            .executes(withSender((ctx, sender) -> executeToggle(sender)))
            .then(argument("message", StringArgumentType.greedyString())
                .executes(withSender((ctx, sender) -> {
                    executeSendMessage(
                        sender,
                        StringArgumentType.getString(ctx, "message")
                    );
                }))
            );
    }

    private void executeToggle(CommandSender sender) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Messages.staffChat().playerOnly());
            return;
        }
        if(!SignedMessageCompat.isSupported(player)) return;
        UUID uuid = player.getUniqueId();
        if(!ENABLED_PLAYERS.contains(uuid)) {
            ENABLED_PLAYERS.add(uuid);
            player.sendMessage(Messages.staffChat().enabled());
        } else {
            ENABLED_PLAYERS.remove(uuid);
            player.sendMessage(Messages.staffChat().disabled());
        }
    }

    public static void executeSendMessage(CommandSender sender, String message) {
        StaffUtils.getServer().broadcast(
            Messages.staffChat().serverFromServer(sender, message),
            Permissions.RECEIVE_STAFFCHAT
        );
        StaffUtils.getSpicord().sendMessage(Messages.staffChat().discordFromServer(sender, message));
    }

}
