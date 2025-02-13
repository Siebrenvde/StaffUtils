package dev.siebrenvde.staffutils.paper;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperCompat {

    @SuppressWarnings("deprecation")
    public static Component displayName(Player player) {
        try {
            return player.displayName();
        } catch(NoSuchMethodError ignored) {
            return Component.text(player.getDisplayName());
        }
    }

    public static boolean isPaper() {
        return hasClass("com.destroystokyo.paper.PaperConfig") || hasClass("io.papermc.paper.configuration.Configuration");
    }

    public static boolean hasBrigadier() {
        return hasClass("io.papermc.paper.command.brigadier.Commands");
    }

    public static boolean hasAsyncChatEvent() {
        return hasClass("io.papermc.paper.event.player.AsyncChatEvent");
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

}
