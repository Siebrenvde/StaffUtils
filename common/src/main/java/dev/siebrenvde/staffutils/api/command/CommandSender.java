package dev.siebrenvde.staffutils.api.command;

import dev.siebrenvde.staffutils.StaffUtils;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface CommandSender {

    /**
     * {@return a new CommandSender instance}
     * @param sender the command sender
     * @param <C> the server software's command sender
     */
    static <C> CommandSender of(C sender) {
        return StaffUtils.getPlatform().getCommandSender(sender);
    }

    /**
     * {@return the name of the command sender}
     */
    String getName();

    /**
     * {@return the display name of the command sender}
     */
    Component getDisplayName();

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
