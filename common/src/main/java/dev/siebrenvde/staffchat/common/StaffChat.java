package dev.siebrenvde.staffchat.common;

import dev.siebrenvde.staffchat.common.spicord.Addon;

public class StaffChat {

    private final MinecraftPlugin plugin;
    private final Addon addon;

    public StaffChat(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.addon = new Addon(plugin);
    }

    public MinecraftPlugin getPlugin() { return plugin; }
    public Addon getAddon() { return addon; }

}
