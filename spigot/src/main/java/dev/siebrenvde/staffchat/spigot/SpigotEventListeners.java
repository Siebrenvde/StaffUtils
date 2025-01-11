package dev.siebrenvde.staffchat.spigot;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.listeners.EventListeners;
import dev.siebrenvde.staffchat.paper.PaperCompat;
import dev.siebrenvde.staffchat.paper.PaperEventListeners;
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
            StaffChat.LOGGER.optional("Using Paper's AsyncChatEvent");
            manager.registerEvents(new PaperEventListeners.ChatListener(), plugin);
        } else {
            StaffChat.LOGGER.optional("Using Spigot's AsyncPlayerChatEvent");
            manager.registerEvents(new ChatListener(), plugin);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        disconnectListener(CommonPlayer.of(event.getPlayer()));
    }

    public static class ChatListener implements Listener {
        @EventHandler
        public void onChat(AsyncPlayerChatEvent event) {
            event.setCancelled(
                EventListeners.chatListener(
                    CommonPlayer.of(event.getPlayer()),
                    event.getMessage()
                )
            );
        }
    }

}
