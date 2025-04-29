package dev.siebrenvde.staffutils.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

@NullMarked
public enum ServerType {
    ALL(null),
    PAPER(miniMessage().deserialize("<gradient:yellow:aqua:green:red:dark_grey>Paper")),
    SPIGOT(text("Spigot", NamedTextColor.GOLD)),
    VELOCITY(text("Velocity", NamedTextColor.AQUA)),
    BUNGEECORD(text("BungeeCord", NamedTextColor.YELLOW));

    private final Component displayName;

    ServerType(@Nullable Component displayName) {
        this.displayName = displayName != null ? displayName : Component.empty();
    }

    public Component displayName() {
        return displayName;
    }
}
