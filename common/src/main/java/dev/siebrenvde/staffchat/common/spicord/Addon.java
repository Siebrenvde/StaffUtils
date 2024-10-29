package dev.siebrenvde.staffchat.common.spicord;

import dev.siebrenvde.staffchat.common.config.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;

import java.util.ArrayList;
import java.util.List;

public class Addon extends SimpleAddon {

    private boolean isReady = false;
    private TextChannel staffChannel;

    private final List<String> messageQueue = new ArrayList<>();

    public Addon() {
        super("StaffChat", "staffchat", "Siebrenvde");
    }

    @Override
    public void onReady(DiscordBot bot) {
        JDA jda = bot.getJda();
        if(Config.CONFIG.staffChannel.isEmpty()) throw new RuntimeException("Staff channel is empty");
        staffChannel = jda.getTextChannelById(Config.CONFIG.staffChannel);
        jda.addEventListener(new MessageListener(this));
        isReady = true;
        staffChannel.sendMessage(String.join("\n", messageQueue)).queue();
    }

    public void sendMessage(String message) {
        if(!isReady) {
            messageQueue.add(message);
            return;
        }
        staffChannel.sendMessage(message).queue();
    }

    public TextChannel getStaffChannel() { return staffChannel; }

}
