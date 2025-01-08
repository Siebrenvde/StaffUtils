package dev.siebrenvde.staffchat.messages;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.player.ProxyPlayer;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.Nullable;

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

    public static TagResolver sender(CommonCommandSender sender) {
        return sender(null, sender);
    }

    public static TagResolver sender(@Subst("sender") @Nullable String prefix, CommonCommandSender sender) {
        prefix = prefix != null ? prefix + "_" : "";
        return TagResolver.resolver(
            Placeholder.unparsed(prefix + "username", sender.getName()),
            StaffChat.getPlatform().isProxy()
                ? Placeholder.unparsed(prefix + "server",
                    sender instanceof ProxyPlayer player
                    ? player.getServer().getName()
                    : "none"
                )
                : Placeholder.unparsed(prefix + "displayname", sender.getDisplayName())
        );
    }

    public static TagResolver discordMember(Member member) {
        Role role = member.getRoles().stream().findFirst().orElse(null);
        return TagResolver.resolver(
            Placeholder.component("profile", Components.discordProfile(member)),
            Placeholder.unparsed("displayname", member.getEffectiveName()),
            Placeholder.unparsed("username", member.getUser().getName()),
            Placeholder.unparsed("role", role != null ? role.getName() : ""),
            Placeholder.styling("effective_colour", TextColor.color(member.getColorRaw())),
            Placeholder.styling("role_colour", role != null
                ? TextColor.color(role.getColorRaw())
                : TextColor.color(Role.DEFAULT_COLOR_RAW)
            )
        );
    }

    public static TagResolver parsedMessage(String message) {
        return Placeholder.component("message", COLOUR_DECORATION.deserialize(message));
    }

}
