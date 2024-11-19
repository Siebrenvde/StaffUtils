package dev.siebrenvde.staffchat.messages;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class Components {

    public static Component discordProfile(Member member) {
        TextComponent.Builder builder = Component.text();

        builder.content(member.getEffectiveName());

        TextComponent.Builder profile = Component.text();
        profile.append(Component.text(member.getEffectiveName(), TextColor.color(member.getColorRaw())));

        if(!member.getEffectiveName().equals(member.getUser().getName())) {
            profile.append(Component.newline().append(Component.text(member.getUser().getName(), NamedTextColor.WHITE)));
        }

        int roleAmount = member.getRoles().size();
        for(int i = 0; i < roleAmount; i++) {
            if(i == 0) {
                profile.append(Component.newline());
            }
            Role role = member.getRoles().get(i);
            profile.append(Component.text(role.getName(), TextColor.color(role.getColorRaw())));
            if(i != (roleAmount - 1)) {
                profile.append(Component.text(" | ", NamedTextColor.GRAY));
            }
        }

        builder.hoverEvent(HoverEvent.showText(profile.build()));
        return builder.build();
    }

}
