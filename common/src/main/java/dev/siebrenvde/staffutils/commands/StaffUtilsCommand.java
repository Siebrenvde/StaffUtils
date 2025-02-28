package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import org.jspecify.annotations.NullMarked;

import static dev.siebrenvde.staffutils.util.BrigadierUtils.hasPermission;
import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;

@NullMarked
public class StaffUtilsCommand extends BaseCommand {

    public StaffUtilsCommand() {
        super(Config.commands().staffUtils, Permissions.COMMAND_STAFFUTILS);
    }

    @Override
    public <C> LiteralArgumentBuilder<C> brigadier(CommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.literal("reload")
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
