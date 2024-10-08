package dev.siebrenvde.staffchat.common.spicord;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;

public class Addon extends SimpleAddon {

    private TextChannel staffChannel;

    public Addon() {
        super("StaffChat", "staffchat", "Siebrenvde");
    }

    @Override
    public void onReady(DiscordBot bot) {
        // TODO: Get TextChannel from config
        bot.getJda().addEventListener(new MessageListener(this));
    }

    public TextChannel getStaffChannel() { return staffChannel; }

}
