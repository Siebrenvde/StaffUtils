package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class StaffUtilsCommand<C> extends BaseCommand<C> {

    public StaffUtilsCommand() {
        super(Config.commands().staffUtils, Permissions.COMMAND_STAFFUTILS);
    }

    @Override
    public LiteralArgumentBuilder<C> builder() {
        return literal(getName())
            .requires(hasPermission(getRootPermission()))
            .then(literal("reload")
                .requires(hasPermission(Permissions.COMMAND_STAFFUTILS_RELOAD))
                .executes(withSender((ctx, sender) -> {
                    executeReload(sender);
                }))
            );
    }

    private void executeReload(CommandSender sender) {
        Config.reload();
        sender.sendMessage(Messages.staffUtils().reloadedConfigs());
    }

}
