package dev.siebrenvde.staffchat.messages;

import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import static net.kyori.adventure.text.Component.text;

public class Components {

    public static Component discordProfile(Member member) {
        TextComponent.Builder profile = text()
            .append(text(member.getEffectiveName(), TextColor.color(member.getColorRaw())));

        if(!member.getEffectiveName().equals(member.getUser().getAsTag())) {
            profile.appendNewline();
            profile.append(text(member.getUser().getAsTag(), NamedTextColor.WHITE));
        }

        if(!member.getRoles().isEmpty()) {
            profile.appendNewline();
            profile.append(Component.join(
                JoinConfiguration.separator(text(" | ", NamedTextColor.GRAY)),
                member.getRoles().stream()
                    .map(role -> text(role.getName(), TextColor.color(role.getColorRaw())))
                    .toList()
            ));
        }

        return text()
            .content(member.getEffectiveName())
            .hoverEvent(HoverEvent.showText(profile))
            .build();
    }

}
