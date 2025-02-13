package dev.siebrenvde.staffutils.api;

import net.kyori.adventure.text.Component;

public enum ServerType {
    ALL(null),
    PAPER("Paper"),
    SPIGOT("Spigot"),
    VELOCITY("Velocity"),
    BUNGEECORD("BungeeCord");

    private final Component displayName;

    ServerType(String displayName) {
        this.displayName = displayName != null ? Component.text(displayName) : Component.empty();
    }

    public Component displayName() {
        return displayName;
    }
}
