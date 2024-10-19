package dev.siebrenvde.staffchat.common;

import dev.siebrenvde.staffchat.common.config.Config;
import dev.siebrenvde.staffchat.common.minecraft.CommandManager;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
import dev.siebrenvde.staffchat.common.minecraft.commands.StaffChatCommand;
import dev.siebrenvde.staffchat.common.spicord.Addon;
import org.spicord.SpicordLoader;

import java.nio.file.Path;

public class StaffChat {

    public static Config CONFIG;

    private static ServerPlatform platform;
    private static CommandManager commandManager;
    private static Addon addon;

    public StaffChat(Path dataDirectory, ServerPlatform serverPlatform, CommandManager cmdManager) {
        CONFIG = Config.load(dataDirectory);
        platform = serverPlatform;
        commandManager = cmdManager;
        addon = new Addon();
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(addon);
        });
    }

    public void registerCommands() {
        commandManager.register(new StaffChatCommand());
    }

    public static ServerPlatform getPlatform() { return platform; }
    public static CommandManager getCommandManager() { return commandManager; }
    public static Addon getAddon() { return addon; }

}
