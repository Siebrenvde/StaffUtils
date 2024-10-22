package dev.siebrenvde.staffchat.common.messages;

import dev.siebrenvde.staffchat.common.StaffChat;
import dev.siebrenvde.staffchat.common.minecraft.CommandSender;
import dev.siebrenvde.staffchat.common.minecraft.ProxyPlayer;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public class Placeholders {

    private static final MiniMessage COLOUR_DECORATION =
        MiniMessage.builder()
            .tags(TagResolver.builder()
                .resolvers(
                    StandardTags.color(),
                    StandardTags.rainbow(),
                    StandardTags.decorations()
                )
                .build()
            )
            .build();

    public static TagResolver sender(CommandSender sender) {
        return TagResolver.resolver(
            Placeholder.unparsed("username", sender.getName()),
            StaffChat.getPlatform().isProxy()
                ? Placeholder.unparsed("server",
                    sender instanceof ProxyPlayer player
                    ? player.getServerName()
                    : "none"
                )
                : Placeholder.unparsed("displayname", sender.getDisplayName())
        );
    }

    public static TagResolver discordMember(Member member) {
        return TagResolver.resolver(
            Placeholder.component("profile", Components.discordProfile(member)),
            Placeholder.unparsed("displayname", member.getEffectiveName()),
            Placeholder.unparsed("username", member.getUser().getName())
        );
    }

    public static TagResolver parsedMessage(String message) {
        return Placeholder.component("message", COLOUR_DECORATION.deserialize(message));
    }

}
