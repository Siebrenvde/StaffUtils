package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.StaffUtils;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

public final class StaffUtilsBungee extends Plugin {

    private static StaffUtilsBungee instance;
    private static BungeeAudiences adventure;

    @Override
    public void onEnable() {
        instance = this;
        adventure = BungeeAudiences.create(this);
        StaffUtils staffUtils = new StaffUtils(
            getDataFolder().toPath(),
            new BungeePlatform(),
            new BungeeGlobalServer(),
            new BungeeLogger(getLogger())
        );
        staffUtils.load();
        staffUtils.registerCommands(new BungeeCommandManager(getProxy().getPluginManager()));
        getProxy().getPluginManager().registerListener(this, new BungeeEventListeners());
    }

    @Override
    public void onDisable() {
        if(adventure != null) adventure.close();
    }

    public static StaffUtilsBungee getInstance() { return instance; }
    public static BungeeAudiences adventure() { return adventure; }

}
