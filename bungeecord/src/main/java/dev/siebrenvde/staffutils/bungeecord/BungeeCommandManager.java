package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BungeeCommandManager implements CommandManager {

    private final PluginManager manager;

    public BungeeCommandManager(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public void register(BaseCommand command) {
        manager.registerCommand(StaffUtilsBungee.getInstance(), new BungeeCommand(command));
    }

    private static class BungeeCommand extends Command implements TabExecutor {

        private final BaseCommand command;

        public BungeeCommand(BaseCommand command) {
            super(command.getName(), command.getRootPermission(), command.getAliases());
            this.command = command;
        }

        @Override
        public void execute(net.md_5.bungee.api.CommandSender sender, String[] args) {
            command.simple(
                CommandSender.of(sender),
                args
            );
        }

        @Override
        public Iterable<String> onTabComplete(net.md_5.bungee.api.CommandSender sender, String[] args) {
            return command.suggestions(
                CommandSender.of(sender),
                args
            );
        }

    }

}
