package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.StaffUtils;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
public final class StaffUtilsBungee extends Plugin {

    @Nullable private static StaffUtilsBungee instance;
    @Nullable private static BungeeAudiences adventure;

    @Override
    public void onEnable() {
        new Metrics(this, 24629);
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

    public static StaffUtilsBungee getInstance() { return Objects.requireNonNull(instance); }
    public static BungeeAudiences adventure() { return Objects.requireNonNull(adventure); }

}
