package dev.siebrenvde.staffutils;

import dev.siebrenvde.staffutils.addons.LuckPermsAddon;
import dev.siebrenvde.staffutils.api.server.Server;
import dev.siebrenvde.staffutils.commands.HelpOpCommand;
import dev.siebrenvde.staffutils.commands.ReportCommand;
import dev.siebrenvde.staffutils.commands.StaffUtilsCommand;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.api.command.CommandManager;
import dev.siebrenvde.staffutils.api.ServerPlatform;
import dev.siebrenvde.staffutils.commands.StaffChatCommand;
import dev.siebrenvde.staffutils.spicord.SpicordAddon;
import dev.siebrenvde.staffutils.util.Logger;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.spicord.SpicordLoader;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@NullMarked
public class StaffUtils {

    @Nullable private static Logger LOGGER;

    @Nullable private static ServerPlatform platform;
    @Nullable private static Server server;
    @Nullable private static SpicordAddon spicordAddon;

    public StaffUtils(Path dataDirectory, ServerPlatform serverPlatform, Server globalServer, Logger logger) {
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

    public <C> void registerCommands(CommandManager<C> manager) {
        manager.registerAll(List.of(
            new StaffChatCommand<>(),
            new ReportCommand<>(),
            new HelpOpCommand<>(),
            new StaffUtilsCommand<>()
        ));
    }

    public static Logger logger() { return Objects.requireNonNull(LOGGER); }
    public static ServerPlatform getPlatform() { return Objects.requireNonNull(platform); }
    public static Server getServer() { return Objects.requireNonNull(server); }
    public static SpicordAddon getSpicord() { return Objects.requireNonNull(spicordAddon); }

}
