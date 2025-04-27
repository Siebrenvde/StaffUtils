package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.spigot.SpigotServer;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public class PaperServer extends SpigotServer {

    @Override
    public Iterable<? extends Audience> audiences() {
        return List.of(Bukkit.getServer());
    }

}
