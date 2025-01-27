package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.StaffUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffUtilsPaper {

    @SuppressWarnings("UnstableApiUsage")
    public static void registerCommands(JavaPlugin plugin, StaffUtils staffUtils) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            staffUtils.registerCommands(new PaperCommandManager(event.registrar()));
        });
    }

    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    public static <S, C> C senderFromSourceStack(S sourceStack) {
        return (C) ((CommandSourceStack) sourceStack).getSender();
    }

}
