package dev.siebrenvde.staffchat.common;

import dev.siebrenvde.staffchat.common.spicord.Addon;
import org.spicord.SpicordLoader;

public class StaffChat {

    private final MinecraftPlugin plugin;
    private final Addon addon;

    public StaffChat(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.addon = new Addon(plugin);
        SpicordLoader.addStartupListener(spicord -> {
            spicord.getAddonManager().registerAddon(addon);
        });
    }

    public MinecraftPlugin getPlugin() { return plugin; }
    public Addon getAddon() { return addon; }

}
