package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.listeners.EventListeners;
import dev.siebrenvde.staffutils.paper.PaperCompat;
import dev.siebrenvde.staffutils.paper.PaperEventListeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotEventListeners extends EventListeners implements Listener {

    public static void register(JavaPlugin plugin) {
        PluginManager manager = plugin.getServer().getPluginManager();

        manager.registerEvents(new SpigotEventListeners(), plugin);

        if(PaperCompat.hasAsyncChatEvent()) {
            StaffUtils.LOGGER.optional("Using Paper's AsyncChatEvent");
            manager.registerEvents(new PaperEventListeners.ChatListener(), plugin);
        } else {
            StaffUtils.LOGGER.optional("Using Spigot's AsyncPlayerChatEvent");
            manager.registerEvents(new ChatListener(), plugin);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        disconnectListener(Player.of(event.getPlayer()));
    }

    public static class ChatListener implements Listener {
        @EventHandler
        public void onChat(AsyncPlayerChatEvent event) {
            event.setCancelled(
                EventListeners.chatListener(
                    Player.of(event.getPlayer()),
                    event.getMessage()
                )
            );
        }
    }

}
