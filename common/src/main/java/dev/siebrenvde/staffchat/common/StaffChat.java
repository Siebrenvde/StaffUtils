package dev.siebrenvde.staffchat.common;

import dev.siebrenvde.staffchat.common.config.Config;
import dev.siebrenvde.staffchat.common.messages.Messages;
import dev.siebrenvde.staffchat.common.minecraft.CommandManager;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
import dev.siebrenvde.staffchat.common.minecraft.commands.StaffChatCommand;
import dev.siebrenvde.staffchat.common.spicord.Addon;
import dev.siebrenvde.staffchat.common.util.Logger;
import org.spicord.SpicordLoader;

import java.nio.file.Path;

public class StaffChat {

    public static Config CONFIG;
    public static Logger LOGGER;

    private static ServerPlatform platform;
    private static Addon addon;

    public StaffChat(Path dataDirectory, ServerPlatform serverPlatform, Logger logger) {
        CONFIG = Config.load(dataDirectory);
        LOGGER = logger;
        platform = serverPlatform;
        addon = new Addon();
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(addon);
        });
        new Messages(platform, CONFIG);
    }

    public void registerCommands(CommandManager manager) {
        manager.register(new StaffChatCommand());
    }

    public static ServerPlatform getPlatform() { return platform; }
    public static Addon getAddon() { return addon; }

}
