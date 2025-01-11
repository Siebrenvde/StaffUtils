package dev.siebrenvde.staffchat.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.listeners.EventListeners;

public class VelocityEventListeners extends EventListeners {

    @SuppressWarnings("deprecation")
    @Subscribe
    public void onPlayerChat(PlayerChatEvent event) {
        if(chatListener(
            CommonPlayer.of(event.getPlayer()),
            event.getMessage()
        )) event.setResult(PlayerChatEvent.ChatResult.denied());
    }

    @Subscribe
    public void onPlayerDisconnect(DisconnectEvent event) {
        disconnectListener(CommonPlayer.of(event.getPlayer()));
    }

}
