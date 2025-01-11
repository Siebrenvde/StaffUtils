package dev.siebrenvde.staffchat;

import dev.siebrenvde.staffchat.api.server.CommonServer;
import dev.siebrenvde.staffchat.commands.HelpOpCommand;
import dev.siebrenvde.staffchat.commands.ReportCommand;
import dev.siebrenvde.staffchat.config.Config;
import dev.siebrenvde.staffchat.messages.Messages;
import dev.siebrenvde.staffchat.api.command.CommandManager;
import dev.siebrenvde.staffchat.api.ServerPlatform;
import dev.siebrenvde.staffchat.commands.StaffChatCommand;
import dev.siebrenvde.staffchat.spicord.Addon;
import dev.siebrenvde.staffchat.util.Logger;
import org.spicord.SpicordLoader;

import java.nio.file.Path;

public class StaffChat {

    public static Logger LOGGER;

    private static ServerPlatform platform;
    private static CommonServer server;
    private static Addon addon;

    public StaffChat(Path dataDirectory, ServerPlatform serverPlatform, CommonServer globalServer, Logger logger) {
        Config.load(dataDirectory);
        LOGGER = logger;
        platform = serverPlatform;
        server = globalServer;
        addon = new Addon();
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(addon);
        });
        new Messages();
    }

    public void registerCommands(CommandManager manager) {
        manager.registerAll(
            new StaffChatCommand(),
            new ReportCommand(),
            new HelpOpCommand()
        );
    }

    public static ServerPlatform getPlatform() { return platform; }
    public static CommonServer getServer() { return server; }
    public static Addon getAddon() { return addon; }

}
