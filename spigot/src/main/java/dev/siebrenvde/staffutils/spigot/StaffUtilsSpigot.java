package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.StaffUtils;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

import static dev.siebrenvde.staffutils.StaffUtils.logger;

@NullMarked
public class StaffUtilsSpigot extends JavaPlugin {

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

        new SpigotEventListeners().register(this);

        registerCommands(staffUtils);

        suggestPaper();
    }

    private void registerCommands(StaffUtils staffUtils) {
        logger().optional("Registering commands using Spigot's outdated system");
        staffUtils.registerCommands(new SpigotCommandManager());
    }

    private void suggestPaper() {
        try {
            Class.forName("io.papermc.paper.configuration.Configuration");
            logger().warn("You're using the Spigot version of this plugin on a Paper server");
            logger().warn("You should download the Paper version for a better experience");
        } catch (ClassNotFoundException ignored) {
            logger().info("It looks like you're using Spigot");
            logger().info("This plugin works better using Paper");
        }
    }

    @Override
    public void onDisable() {
        if(adventure != null) adventure.close();
    }

    public static StaffUtilsSpigot getInstance() { return Objects.requireNonNull(instance); }
    public static BukkitAudiences adventure() { return Objects.requireNonNull(adventure); }

}
