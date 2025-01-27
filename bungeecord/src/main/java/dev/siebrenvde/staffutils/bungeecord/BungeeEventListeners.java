package dev.siebrenvde.staffutils.bungeecord;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.listeners.EventListeners;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeEventListeners extends EventListeners implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        if(event.isCommand()) return;
        event.setCancelled(chatListener(
            Player.of(event.getSender()),
            event.getMessage()
        ));
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        disconnectListener(Player.of(event.getPlayer()));
    }

}
