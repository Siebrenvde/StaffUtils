package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import dev.siebrenvde.staffutils.paper.StaffUtilsPaper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffUtilsSpigot extends JavaPlugin {

    private static StaffUtilsSpigot instance;
    private static BukkitAudiences adventure;

    @Override
    public void onEnable() {
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
            StaffUtils.LOGGER.optional("Registering commands using Brigadier");
            StaffUtilsPaper.registerCommands(this, staffUtils);
        } else {
            StaffUtils.LOGGER.optional("Registering commands using Spigot's outdated system");
            staffUtils.registerCommands(new SpigotCommandManager());
        }

        if(!PaperCompat.isPaper()) {
            StaffUtils.LOGGER.info("It looks like you're using Spigot");
            StaffUtils.LOGGER.info("This plugin works better using Paper");
        }
    }

    @Override
    public void onDisable() {
        adventure.close();
    }

    public static StaffUtilsSpigot getInstance() { return instance; }
    public static BukkitAudiences adventure() { return adventure; }

}
