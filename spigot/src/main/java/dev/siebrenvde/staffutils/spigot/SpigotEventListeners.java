package dev.siebrenvde.staffutils.spigot;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.listeners.EventListeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SpigotEventListeners extends EventListeners implements Listener {

    public static void register(JavaPlugin plugin) {
        PluginManager manager = plugin.getServer().getPluginManager();

        SpigotEventListeners instance = new SpigotEventListeners();
        manager.registerEvents(instance, plugin);
        manager.registerEvents(instance.getChatListener(), plugin);
    }

    protected Listener getChatListener() {
        StaffUtils.logger().optional("Using Spigot's AsyncPlayerChatEvent");
        return new ChatListener();
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
