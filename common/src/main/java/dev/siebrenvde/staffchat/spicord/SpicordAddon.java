package dev.siebrenvde.staffchat.spicord;

import dev.siebrenvde.staffchat.config.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;

public class SpicordAddon extends SimpleAddon {

    private boolean isReady = false;
    private TextChannel staffChannel;

    public SpicordAddon() {
        super("StaffChat", "staffchat", "Siebrenvde");
    }

    @Override
    public void onReady(DiscordBot bot) {
        JDA jda = bot.getJda();
        if(Config.CONFIG.staffChannel.getRealValue().isEmpty()) throw new RuntimeException("Staff channel is empty");
        staffChannel = jda.getTextChannelById(Config.CONFIG.staffChannel.getRealValue());
        jda.addEventListener(new MessageListener(this));
        isReady = true;
    }

    public void sendMessage(String message) {
        if(isReady) staffChannel.sendMessage(message).queue();
    }

    public TextChannel getStaffChannel() { return staffChannel; }

}
