package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import dev.siebrenvde.staffutils.paper.StaffUtilsPaper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
public final class StaffUtilsSpigot extends JavaPlugin {

    @Nullable private static StaffUtilsSpigot instance;
    @Nullable private static BukkitAudiences adventure;

    @Override
    public void onEnable() {
        new Metrics(this, 24627);
        instance = this;
        adventure = BukkitAudiences.create(this);
        StaffUtils staffUtils = new StaffUtils(
            getDataFolder().toPath(),
            new SpigotPlatform(),
            new SpigotServer(),
            new SpigotLogger(getLogger())
        );
        staffUtils.load();

        SpigotEventListeners.register(this);

        if(PaperCompat.hasBrigadier()) {
            StaffUtils.logger().optional("Registering commands using Brigadier");
            StaffUtilsPaper.registerCommands(this, staffUtils);
        } else {
            StaffUtils.logger().optional("Registering commands using Spigot's outdated system");
            staffUtils.registerCommands(new SpigotCommandManager());
        }

        if(!PaperCompat.isPaper()) {
            StaffUtils.logger().info("It looks like you're using Spigot");
            StaffUtils.logger().info("This plugin works better using Paper");
        }
    }

    @Override
    public void onDisable() {
        if(adventure != null) adventure.close();
    }

    public static StaffUtilsSpigot getInstance() { return Objects.requireNonNull(instance); }
    public static BukkitAudiences adventure() { return Objects.requireNonNull(adventure); }

}
