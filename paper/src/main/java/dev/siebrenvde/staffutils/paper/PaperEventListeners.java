package dev.siebrenvde.staffutils.paper;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.listeners.EventListeners;
import dev.siebrenvde.staffutils.spigot.SpigotEventListeners;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperEventListeners extends SpigotEventListeners {

    @Override
    protected Listener getChatListener() {
        StaffUtils.logger().optional("Using Paper's AsyncChatEvent");
        return new ChatListener();
    }

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
