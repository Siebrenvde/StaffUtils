package dev.siebrenvde.staffchat.common.spicord;

import dev.siebrenvde.staffchat.common.StaffChat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;

public class Addon extends SimpleAddon {

    private JDA jda;
    private TextChannel staffChannel;

    public Addon() {
        super("StaffChat", "staffchat", "Siebrenvde");
    }

    @Override
    public void onReady(DiscordBot bot) {
        jda = bot.getJda();
        if(StaffChat.CONFIG.staffChannel.isEmpty()) throw new RuntimeException("Staff channel is empty");
        staffChannel = jda.getTextChannelById(StaffChat.CONFIG.staffChannel);
        jda.addEventListener(new MessageListener(this));
    }

    public TextChannel getStaffChannel() { return staffChannel; }

}
