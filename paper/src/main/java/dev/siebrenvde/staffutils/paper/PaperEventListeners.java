package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.listeners.EventListeners;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PaperEventListeners {

    public static class ChatListener implements Listener {
        @EventHandler
        public void onChat(AsyncChatEvent event) {
            event.setCancelled(
                EventListeners.chatListener(
                    Player.of(event.getPlayer()),
                    PlainTextComponentSerializer.plainText().serialize(event.message())
                )
            );
        }
    }

}
