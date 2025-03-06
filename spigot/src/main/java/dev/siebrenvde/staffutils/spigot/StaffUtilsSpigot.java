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
        try {
            adventure = BukkitAudiences.create(this);
        } catch (NoClassDefFoundError ignored) {}
        StaffUtils staffUtils = new StaffUtils(
            getDataFolder().toPath(),
            createPlatform(),
            new SpigotServer(),
            new SpigotLogger(getLogger())
        );
        staffUtils.load();

        registerListeners();

        registerCommands(staffUtils);

        suggestPaper();
    }

    protected SpigotPlatform createPlatform() {
        return new SpigotPlatform();
    }

    protected void registerListeners() {
        new SpigotEventListeners().register(this);
    }

    protected void registerCommands(StaffUtils staffUtils) {
        logger().optional("Registering commands using Spigot's outdated system");
        staffUtils.registerCommands(new SpigotCommandManager());
    }

    protected void suggestPaper() {
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
