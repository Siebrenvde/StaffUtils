package dev.siebrenvde.staffutils.api.command;

import dev.siebrenvde.staffutils.StaffUtils;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface CommandSender extends ForwardingAudience.Single {

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
    default Component getDisplayName() {
        return get(Identity.DISPLAY_NAME).orElse(Component.text(getName()));
    }

    /**
     * {@return whether the command sender has the provided permission}
     * @param permission the permission to check
     */
    boolean hasPermission(String permission);

}
