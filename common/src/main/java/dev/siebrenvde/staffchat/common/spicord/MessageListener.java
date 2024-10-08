package dev.siebrenvde.staffchat.common.spicord;

import dev.siebrenvde.staffchat.common.StaffChat;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
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

        // TODO: Convert to MiniMessage
        StaffChat.getPlatform().broadcast(Component.empty(), "staffchat.see");

    }

}
