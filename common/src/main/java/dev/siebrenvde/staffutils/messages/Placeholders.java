package dev.siebrenvde.staffutils.messages;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.addons.LuckPermsAddon;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.api.player.ProxyPlayer;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.luckperms.api.model.group.Group;
import org.intellij.lang.annotations.Subst;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.minimessage.tag.resolver.Placeholder.*;

@NullMarked
public class Placeholders {

    private static final MiniMessage COLOUR_DECORATION =
        MiniMessage.builder()
            .tags(TagResolver.builder()
                .resolvers(
                    StandardTags.color(),
                    StandardTags.rainbow(),
                    StandardTags.pride(),
                    StandardTags.gradient(),
                    StandardTags.decorations()
                )
                .build()
            )
            .build();

    public static TagResolver sender(CommandSender sender) {
        return sender(null, sender);
    }

    public static TagResolver sender(@Subst("sender") @Nullable String prefix, CommandSender sender) {
        prefix = prefix != null ? prefix + "_" : "";
        List<TagResolver> resolvers = new ArrayList<>();
        resolvers.add(unparsed(prefix + "username", sender.getName()));

        if(StaffUtils.getPlatform().isProxy()) {
            resolvers.add(unparsed(prefix + "server",
                sender instanceof ProxyPlayer player
                    ? player.getServer().getName()
                    : "none"
            ));
        } else {
            resolvers.add(component(prefix + "displayname", sender.getDisplayName()));
        }

        if(LuckPermsAddon.get().isLoaded()) {
            Group group = null;
            Component playerPrefix = Component.empty();
            Component playerSuffix = Component.empty();
            if(sender instanceof Player player) {
                group = LuckPermsAddon.get().getPlayerGroup(player).orElse(null);
                playerPrefix = LuckPermsAddon.get().getPlayerPrefix(player).orElse(Component.empty());
                playerSuffix = LuckPermsAddon.get().getPlayerSuffix(player).orElse(Component.empty());
            }
            resolvers.add(unparsed(prefix + "luckperms_group_name", group != null ? group.getName() : "none"));
            resolvers.add(unparsed(
                prefix + "luckperms_group_displayname",
                group != null
                    ? group.getDisplayName() != null ? group.getDisplayName() : group.getName()
                    : "none"
            ));
            resolvers.add(component(prefix + "luckperms_prefix", playerPrefix));
            resolvers.add(component(prefix + "luckperms_suffix", playerSuffix));
        }

        return TagResolver.resolver(resolvers);
    }

    public static TagResolver discordMember(Member member) {
        Role role = member.getRoles().stream().findFirst().orElse(null);
        return TagResolver.resolver(
            component("profile", Components.discordProfile(member)),
            unparsed("displayname", member.getEffectiveName()),
            unparsed("username", member.getUser().getName()),
            unparsed("role", role != null ? role.getName() : ""),
            styling("effective_colour", TextColor.color(member.getColorRaw())),
            styling("role_colour", role != null
                ? TextColor.color(role.getColorRaw())
                : TextColor.color(Role.DEFAULT_COLOR_RAW)
            )
        );
    }

    public static TagResolver formattedMessage(String message) {
        return component("message", COLOUR_DECORATION.deserialize(message));
    }

}
