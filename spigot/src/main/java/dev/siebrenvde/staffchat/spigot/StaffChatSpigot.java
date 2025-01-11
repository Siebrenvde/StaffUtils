package dev.siebrenvde.staffchat.spigot;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.paper.PaperCompat;
import dev.siebrenvde.staffchat.paper.StaffChatPaper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffChatSpigot extends JavaPlugin {

    private static StaffChatSpigot instance;
    private static BukkitAudiences adventure;

    @Override
    public void onEnable() {
        instance = this;
        adventure = BukkitAudiences.create(this);
        StaffChat staffChat = new StaffChat(
            getDataFolder().toPath(),
            new SpigotPlatform(),
            new SpigotServer(),
            new SpigotLogger(getLogger())
        );

        SpigotEventListeners.register(this);

        if(PaperCompat.hasBrigadier()) {
            StaffChat.LOGGER.optional("Registering commands using Brigadier");
            StaffChatPaper.registerCommands(this, staffChat);
        } else {
            StaffChat.LOGGER.optional("Registering commands using Spigot's outdated system");
            staffChat.registerCommands(new SpigotCommandManager());
        }
    }

    @Override
    public void onDisable() {
        adventure.close();
    }

    public static StaffChatSpigot getInstance() { return instance; }
    public static BukkitAudiences adventure() { return adventure; }

}