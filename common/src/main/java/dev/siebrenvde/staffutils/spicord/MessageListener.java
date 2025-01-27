package dev.siebrenvde.staffutils.spicord;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.Permissions;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    private final SpicordAddon addon;

    public MessageListener(SpicordAddon addon) {
        this.addon = addon;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        User author = event.getAuthor();

        if(
            !(event.getChannel() instanceof TextChannel channel)
            || !channel.equals(addon.getStaffChannel())
            || author.isBot()
        ) return;

        StaffUtils.getServer().broadcast(
            Messages.staffChat().serverFromDiscord(
                addon.getStaffChannel().getGuild().getMember(author),
                event.getMessage().getContentStripped()
            ),
            Permissions.RECEIVE_STAFFCHAT
        );

    }

}
