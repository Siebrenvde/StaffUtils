package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.paper.command.PaperCommandArguments;
import dev.siebrenvde.staffutils.paper.command.PaperCommandManager;
import dev.siebrenvde.staffutils.spigot.SpigotLogger;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class StaffUtilsPaper extends JavaPlugin {

    @Override
    public void onEnable() {
        new Metrics(this, 24627);
        StaffUtils staffUtils = new StaffUtils(
            getDataFolder().toPath(),
            new PaperPlatform(),
            new PaperServer(),
            new SpigotLogger(getLogger()),
            new PaperCommandArguments()
        );
        staffUtils.load();

        new PaperEventListeners().register(this);

        registerCommands(staffUtils);
    }

    @SuppressWarnings("UnstableApiUsage")
    protected void registerCommands(StaffUtils staffUtils) {
        StaffUtils.logger().optional("Registering commands using Brigadier");
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            staffUtils.registerCommands(new PaperCommandManager(event.registrar()));
        });
    }

}
