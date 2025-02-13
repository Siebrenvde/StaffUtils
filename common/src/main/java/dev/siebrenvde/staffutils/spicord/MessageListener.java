package dev.siebrenvde.staffutils.spicord;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public class MessageListener extends ListenerAdapter {

    private final SpicordAddon addon;

    public MessageListener(SpicordAddon addon) {
        this.addon = addon;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(
            !(event.getChannel() instanceof TextChannel channel)
            || !channel.equals(addon.getStaffChannel())
            || event.getAuthor().isBot()
        ) return;

        StaffUtils.getServer().broadcast(
            Messages.staffChat().serverFromDiscord(
                Objects.requireNonNull(event.getMember()),
                event.getMessage().getContentStripped()
            ),
            Permissions.RECEIVE_STAFFCHAT
        );
    }

}
