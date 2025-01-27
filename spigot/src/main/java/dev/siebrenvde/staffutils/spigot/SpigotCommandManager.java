package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.api.command.CommonCommandSender;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class SpigotCommandManager implements CommandManager {

    @Override
    public void register(BaseCommand command) {
        SimpleCommandMap commandMap;
        PluginCommand pluginCommand;

        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            commandMap = (SimpleCommandMap) bukkitCommandMap.get(Bukkit.getServer());

            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            pluginCommand = constructor.newInstance(command.getName(), StaffUtilsSpigot.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to register command '%s'", command.getName()), e);
        }

        pluginCommand.setAliases(Arrays.asList(command.getAliases()));
        if(command.getDescription() != null) pluginCommand.setDescription(command.getDescription());

        SpigotCommand spigotCommand = new SpigotCommand(command);
        pluginCommand.setExecutor(spigotCommand);
        pluginCommand.setTabCompleter(spigotCommand);

        commandMap.register(command.getName(), "staffchat", pluginCommand);
    }

    private record SpigotCommand(BaseCommand command) implements TabExecutor {

        @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
                command.simple(
                    CommonCommandSender.of(sender),
                    args
                );
                return true;
            }

        @Override
        public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
            return command.suggestions(
                CommonCommandSender.of(sender),
                args
            );
        }
    }

}
