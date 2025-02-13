package dev.siebrenvde.staffutils.spicord;

import dev.siebrenvde.staffutils.config.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;

@NullMarked
public class SpicordAddon extends SimpleAddon {

    private boolean isReady = false;
    @Nullable private TextChannel staffChannel;

    public SpicordAddon() {
        super("StaffUtils", "staffutils", "Siebrenvde");
    }

    @Override
    public void onReady(DiscordBot bot) {
        JDA jda = bot.getJda();
        if(Config.config().staffChannel.getRealValue().isEmpty()) throw new RuntimeException("Staff channel is empty");
        staffChannel = jda.getTextChannelById(Config.config().staffChannel.getRealValue());
        jda.addEventListener(new MessageListener(this));
        isReady = true;
    }

    @Override
    public void onShutdown(DiscordBot bot) {
        staffChannel = null;
        isReady = false;
    }

    public void sendMessage(String message) {
        if(isReady && staffChannel != null) staffChannel.sendMessage(message).queue();
    }

    public @Nullable TextChannel getStaffChannel() { return staffChannel; }

}
