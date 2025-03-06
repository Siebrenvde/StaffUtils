package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.spigot.SpigotPlatform;
import dev.siebrenvde.staffutils.spigot.StaffUtilsSpigot;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class StaffUtilsPaper extends StaffUtilsSpigot {

    @Override
    protected SpigotPlatform createPlatform() {
        return new PaperPlatform();
    }

    @Override
    protected void registerListeners() {
        new PaperEventListeners().register(this);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected void registerCommands(StaffUtils staffUtils) {
        StaffUtils.logger().optional("Registering commands using Brigadier");
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            staffUtils.registerCommands(new PaperCommandManager(event.registrar()));
        });
    }

    @Override
    protected void suggestPaper() {} // Disable

}
