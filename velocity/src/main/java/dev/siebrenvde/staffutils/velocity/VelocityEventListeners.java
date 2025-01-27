package dev.siebrenvde.staffutils.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.listeners.EventListeners;

public class VelocityEventListeners extends EventListeners {

    @SuppressWarnings("deprecation")
    @Subscribe
    public void onPlayerChat(PlayerChatEvent event) {
        if(chatListener(
            Player.of(event.getPlayer()),
            event.getMessage()
        )) event.setResult(PlayerChatEvent.ChatResult.denied());
    }

    @Subscribe
    public void onPlayerDisconnect(DisconnectEvent event) {
        disconnectListener(Player.of(event.getPlayer()));
    }

}
