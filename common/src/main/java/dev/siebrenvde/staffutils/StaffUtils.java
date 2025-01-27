package dev.siebrenvde.staffutils;

import dev.siebrenvde.staffutils.addons.LuckPermsAddon;
import dev.siebrenvde.staffutils.api.server.CommonServer;
import dev.siebrenvde.staffutils.commands.HelpOpCommand;
import dev.siebrenvde.staffutils.commands.ReportCommand;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.commands.StaffChatCommand;
import dev.siebrenvde.staffutils.spicord.SpicordAddon;
import dev.siebrenvde.staffutils.util.Logger;
import org.spicord.SpicordLoader;

import java.nio.file.Path;

public class StaffUtils {

    public static Logger LOGGER;

    private static ServerPlatform platform;
    private static CommonServer server;
    private static SpicordAddon spicordAddon;

    public StaffUtils(Path dataDirectory, ServerPlatform serverPlatform, CommonServer globalServer, Logger logger) {
        LOGGER = logger;
        platform = serverPlatform;
        server = globalServer;
        Config.load(dataDirectory);
    }

    public void load() {
        spicordAddon = new SpicordAddon();
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(spicordAddon);
        });
        new Messages();
        new LuckPermsAddon();
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
    public static SpicordAddon getSpicord() { return spicordAddon; }

}
