package dev.siebrenvde.staffchat.paper;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.listeners.EventListeners;
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
                    CommonPlayer.of(event.getPlayer()),
                    PlainTextComponentSerializer.plainText().serialize(event.message())
                )
            );
        }
    }

}
