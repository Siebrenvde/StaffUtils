package dev.siebrenvde.staffchat.common.spicord;

import dev.siebrenvde.staffchat.common.minecraft.MinecraftPlugin;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;

public class Addon extends SimpleAddon {

    private final MinecraftPlugin plugin;
    private TextChannel staffChannel;

    public Addon(MinecraftPlugin plugin) {
        super("StaffChat", "staffchat", "Siebrenvde");
        this.plugin = plugin;
    }

    @Override
    public void onReady(DiscordBot bot) {
        // TODO: Get TextChannel from config
        bot.getJda().addEventListener(new MessageListener(this, plugin));
    }

    public TextChannel getStaffChannel() { return staffChannel; }

}
