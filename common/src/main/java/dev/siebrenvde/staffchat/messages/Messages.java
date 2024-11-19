package dev.siebrenvde.staffchat.messages;

import dev.siebrenvde.staffchat.config.MessageConfig;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.ServerPlatform;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

/**
 * Parses MiniMessage messages from the config
 */
public class Messages {

    private final MiniMessage miniMessage;
    private final MessageConfig config;

    private static Messages instance;
    private final StaffChat staffChat;

    public Messages(ServerPlatform platform, MessageConfig config) {
        instance = this;
        miniMessage = MiniMessage.miniMessage();
        this.config = config;
        staffChat = new StaffChat(platform, config, miniMessage);
    }

    public static Messages messages() { return instance; }
    public static StaffChat staffChat() { return instance.staffChat; }

    public Component permissionMessage() {
        return miniMessage.deserialize(config.permissionMessage);
    }

    public record StaffChat(ServerPlatform platform, MessageConfig config, MiniMessage miniMessage) {

        /**
         * The message sent in-game when using the staffchat command
         * @param sender the message sender
         * @param message the message
         * @return the message parsed as a Component
         */
        public Component serverFromServer(CommonCommandSender sender, String message) {
            return miniMessage().deserialize(
                platform.isProxy()
                    ? config().staffChat.proxyFromProxy
                    : config().staffChat.serverFromServer,
                Placeholders.sender(sender),
                Placeholders.parsedMessage(message) // TODO: Add config option to disable parsing
            );
        }

        public Component serverFromDiscord(Member author, String message) {
            return miniMessage().deserialize(
                config().staffChat.gameFromDiscord,
                Placeholders.discordMember(author),
                Placeholders.parsedMessage(message)
            );
        }

        public String discordFromServer(CommonCommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage.deserialize(
                    platform.isProxy()
                        ? config().staffChat.discordFromProxy
                        : config().staffChat.discordFromServer,
                    Placeholders.sender(sender),
                    Placeholders.parsedMessage(message)
                )
            );
        }

        public Component playerOnly() { return miniMessage().deserialize(config().staffChat.playerOnly); }
        public Component enabled() { return miniMessage().deserialize(config().staffChat.enabled); }
        public Component disabled() { return miniMessage().deserialize(config().staffChat.disabled); }

    }

}
