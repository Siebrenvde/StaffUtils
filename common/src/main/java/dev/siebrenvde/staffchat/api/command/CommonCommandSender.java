package dev.siebrenvde.staffchat.api.command;

import dev.siebrenvde.staffchat.StaffChat;
import net.kyori.adventure.text.Component;

public interface CommonCommandSender {

    /**
     * {@return a new CommandSender instance}
     * @param sender the command sender
     * @param <C> the server software's command sender
     */
    static <C> CommonCommandSender of(C sender) {
        return StaffChat.getPlatform().getCommandSender(sender);
    }

    /**
     * {@return the name of the command sender}
     */
    String getName();

    /**
     * {@return the display name of the command sender}
     */
    String getDisplayName();

    /**
     * Sends a message to the command sender
     * @param message the message to send
     */
    void sendMessage(Component message);

    /**
     * {@return whether the command sender has the provided permission}
     * @param permission the permission to check
     */
    boolean hasPermission(String permission);

}
