package dev.siebrenvde.staffchat.paper;

import dev.siebrenvde.staffchat.StaffChat;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffChatPaper {

    @SuppressWarnings("UnstableApiUsage")
    public static void registerCommands(JavaPlugin plugin, StaffChat staffChat) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            staffChat.registerCommands(new PaperCommandManager(event.registrar()));
        });
    }

    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    public static <S, C> C senderFromSourceStack(S sourceStack) {
        return (C) ((CommandSourceStack) sourceStack).getSender();
    }

}
