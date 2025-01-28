package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;

import java.util.List;

import static dev.siebrenvde.staffutils.util.BrigadierUtils.hasPermission;
import static dev.siebrenvde.staffutils.util.BrigadierUtils.withSender;

public class StaffUtilsCommand extends BaseCommand {

    public StaffUtilsCommand() {
        super(Config.COMMANDS.staffUtils, Permissions.COMMAND_STAFFUTILS);
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
        return manager.literal(getName())
            .then(manager.literal("reload")
                .requires(hasPermission(Permissions.COMMAND_STAFFUTILS_RELOAD))
                .executes(withSender((ctx, sender) -> {
                    executeReload(sender);
                }))
            )
            .build();
    }

    @Override
    public void simple(CommandSender sender, String[] args) {
        if(!checkPermission(sender, getRootPermission())) return;
        if(args.length == 1 && args[0].equals("reload") && checkPermission(sender, Permissions.COMMAND_STAFFUTILS_RELOAD)) {
            executeReload(sender);
        }
    }

    @Override
    public List<String> suggestions(CommandSender sender, String[] args) {
        if(args.length == 1 && "reload".startsWith(args[0].toLowerCase())) return List.of("reload");
        return List.of();
    }

    private void executeReload(CommandSender sender) {
        Config.reload();
        sender.sendMessage(Messages.staffUtils().reloadedConfigs());
    }

}
