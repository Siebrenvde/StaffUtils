package dev.siebrenvde.staffchat.common.messages;

import dev.siebrenvde.staffchat.common.config.Config;
import dev.siebrenvde.staffchat.common.minecraft.CommandSender;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

/**
 * Parses MiniMessage messages from the config
 */
public class Messages {

    private static Messages instance;

    private final StaffChat staffChat;

    public Messages(ServerPlatform platform, Config config) {
        instance = this;
        MiniMessage miniMessage = MiniMessage.miniMessage();
        staffChat = new StaffChat(platform, config, miniMessage);
    }

    public static Messages messages() { return instance; }
    public static StaffChat staffChat() { return instance.staffChat; }

    public record StaffChat(ServerPlatform platform, Config config, MiniMessage miniMessage) {

        /**
         * The message sent in-game when using the staffchat command
         * @param sender the message sender
         * @param message the message
         * @return the message parsed as a Component
         */
        public Component serverFromServer(CommandSender sender, String message) {
            return miniMessage().deserialize(
                platform.isProxy()
                    ? config().messages.staffChat.proxyFromProxy
                    : config().messages.staffChat.serverFromServer,
                Placeholders.sender(sender),
                Placeholders.parsedMessage(message) // TODO: Add config option to disable parsing
            );
        }

        public Component serverFromDiscord(Member author, String message) {
            return miniMessage().deserialize(
                config().messages.staffChat.gameFromDiscord,
                Placeholders.discordMember(author),
                Placeholders.parsedMessage(message)
            );
        }

        public String discordFromServer(CommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage.deserialize(
                    platform.isProxy()
                        ? config().messages.staffChat.discordFromProxy
                        : config().messages.staffChat.discordFromServer,
                    Placeholders.sender(sender),
                    Placeholders.parsedMessage(message)
                )
            );
        }

    }

}
