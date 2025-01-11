package dev.siebrenvde.staffchat.listeners;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.commands.StaffChatCommand;

public class EventListeners {

    public static boolean chatListener(CommonPlayer player, String message) {
        if(!StaffChatCommand.ENABLED_PLAYERS.contains(player.getUniqueId())) return false;
        StaffChatCommand.executeSendMessage(player, message);
        return true;
    }

    public static void disconnectListener(CommonPlayer player) {
        StaffChatCommand.ENABLED_PLAYERS.remove(player.getUniqueId());
    }

}
