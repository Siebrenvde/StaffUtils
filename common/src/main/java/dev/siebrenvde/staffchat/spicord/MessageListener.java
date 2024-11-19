package dev.siebrenvde.staffchat.spicord;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.messages.Messages;
import dev.siebrenvde.staffchat.util.Permissions;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    private final Addon addon;

    public MessageListener(Addon addon) {
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

        StaffChat.getPlatform().broadcast(
            Messages.staffChat().serverFromDiscord(
                addon.getStaffChannel().getGuild().getMember(author),
                event.getMessage().getContentStripped()
            ),
            Permissions.RECEIVE_STAFFCHAT
        );

    }

}
