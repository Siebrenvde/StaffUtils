package dev.siebrenvde.staffchat.bungeecord;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.listeners.EventListeners;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeEventListeners extends EventListeners implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        if(event.isCommand()) return;
        event.setCancelled(chatListener(
            CommonPlayer.of(event.getSender()),
            event.getMessage()
        ));
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        disconnectListener(CommonPlayer.of(event.getPlayer()));
    }

}
