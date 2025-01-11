package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.StaffChat;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

public final class StaffChatBungee extends Plugin {

    private static StaffChatBungee instance;
    private static BungeeAudiences adventure;

    @Override
    public void onEnable() {
        instance = this;
        adventure = BungeeAudiences.create(this);
        StaffChat staffChat = new StaffChat(
            getDataFolder().toPath(),
            new BungeePlatform(),
            new BungeeGlobalServer(),
            new BungeeLogger(getLogger())
        );
        staffChat.registerCommands(new BungeeCommandManager(getProxy().getPluginManager()));
        getProxy().getPluginManager().registerListener(this, new BungeeEventListeners());
    }

    @Override
    public void onDisable() {
        if(adventure != null) adventure.close();
    }

    public static StaffChatBungee getInstance() { return instance; }
    public static BungeeAudiences adventure() { return adventure; }

}
