package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.command.CommandManager;
import dev.siebrenvde.staffchat.api.command.BaseCommand;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.plugin.TabExecutor;

public class BungeeCommandManager implements CommandManager {

    private final PluginManager manager;

    public BungeeCommandManager(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public void register(BaseCommand command) {
        manager.registerCommand(StaffChatBungee.getInstance(), new BungeeCommand(command));
    }

    private static class BungeeCommand extends Command implements TabExecutor {

        private final BaseCommand command;

        public BungeeCommand(BaseCommand command) {
            super(command.getName(), command.getRootPermission(), command.getAliases());
            this.command = command;
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            command.simple(
                CommonCommandSender.of(sender),
                args
            );
        }

        @Override
        public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
            return command.suggestions(
                CommonCommandSender.of(sender),
                args
            );
        }

    }

}
