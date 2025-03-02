package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BungeeCommandManager extends BrigadierCommandManager<CommandSender> {

    private final PluginManager manager;

    public BungeeCommandManager(PluginManager manager) {
        this.manager = manager;
    }

    @Override
    public void register(BaseCommand<CommandSender> command) {
        super.register(command);
        manager.registerCommand(StaffUtilsBungee.getInstance(), new BungeeCommand(command, this));
    }

    private static class BungeeCommand extends Command implements TabExecutor {

        private final BaseCommand<CommandSender> command;
        private final BungeeCommandManager manager;

        public BungeeCommand(BaseCommand<CommandSender> command, BungeeCommandManager manager) {
            super(command.getName(), command.getRootPermission(), command.getAliases());
            this.command = command;
            this.manager = manager;
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            manager.execute(sender, command, args);
        }

        @Override
        public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
            return manager.suggest(sender, command, args);
        }

    }

}
