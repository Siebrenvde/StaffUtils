package dev.siebrenvde.staffchat.common;

import dev.siebrenvde.staffchat.common.commands.StaffChatCommand;
import dev.siebrenvde.staffchat.common.spicord.Addon;
import org.incendo.cloud.CommandManager;
import org.spicord.SpicordLoader;

public class StaffChat {

    private final MinecraftPlugin plugin;
    private final Addon addon;

    public <C> StaffChat(MinecraftPlugin plugin, CommandManager<C> commandManager) {
        this.plugin = plugin;
        this.addon = new Addon(plugin);
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(addon);
        });
        StaffChatCommand.register(commandManager, plugin);
    }

    public MinecraftPlugin getPlugin() { return plugin; }
    public Addon getAddon() { return addon; }

}
