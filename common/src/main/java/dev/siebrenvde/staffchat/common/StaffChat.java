package dev.siebrenvde.staffchat.common;

import dev.siebrenvde.staffchat.common.minecraft.commands.StaffChatCommand;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
import dev.siebrenvde.staffchat.common.spicord.Addon;
import org.incendo.cloud.CommandManager;
import org.spicord.SpicordLoader;

public class StaffChat {

    private static ServerPlatform platform;
    private static Addon addon;

    public <C> StaffChat(ServerPlatform serverPlatform, CommandManager<C> commandManager) {
        platform = serverPlatform;
        addon = new Addon();
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(addon);
        });
        StaffChatCommand.register(commandManager, platform);
    }

    public static ServerPlatform getPlatform() { return platform; }
    public static Addon getAddon() { return addon; }

}
