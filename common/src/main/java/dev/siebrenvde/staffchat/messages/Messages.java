package dev.siebrenvde.staffchat.messages;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.config.MessageConfig;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.ServerPlatform;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

/**
 * Parses MiniMessage messages from the config
 */
public class Messages {

    private final MiniMessage miniMessage;
    private final MessageConfig config;

    private static Messages instance;
    private final StaffChat staffChat;
    private final Report report;
    private final HelpOp helpOp;

    public Messages(ServerPlatform platform, MessageConfig config) {
        instance = this;
        miniMessage = MiniMessage.miniMessage();
        this.config = config;
        staffChat = new StaffChat(platform, config.staffChat, miniMessage);
        report = new Report(platform, config.report, miniMessage);
        helpOp = new HelpOp(platform, config.helpOp, miniMessage);
    }

    public static Messages messages() { return instance; }
    public static StaffChat staffChat() { return instance.staffChat; }
    public static Report report() { return instance.report; }
    public static HelpOp helpOp() { return instance.helpOp; }

    public Component permissionMessage() {
        return miniMessage.deserialize(config.permissionMessage);
    }

    public record StaffChat(ServerPlatform platform, MessageConfig.StaffChat config, MiniMessage miniMessage) {

        /**
         * The message sent in-game when using the staffchat command
         * @param sender the message sender
         * @param message the message
         * @return the message parsed as a Component
         */
        public Component serverFromServer(CommonCommandSender sender, String message) {
            return miniMessage().deserialize(
                platform.isProxy()
                    ? config().proxyFromProxy
                    : config().serverFromServer,
                Placeholders.sender(sender),
                Placeholders.formattedMessage(message) // TODO: Add config option to disable parsing
            );
        }

        public Component serverFromDiscord(Member author, String message) {
            return miniMessage().deserialize(
                config().gameFromDiscord,
                Placeholders.discordMember(author),
                Placeholders.formattedMessage(message)
            );
        }

        public String discordFromServer(CommonCommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage.deserialize(
                    platform.isProxy()
                        ? config().discordFromProxy
                        : config().discordFromServer,
                    Placeholders.sender(sender),
                    Placeholders.formattedMessage(message)
                )
            );
        }

        public Component playerOnly() { return miniMessage().deserialize(config().playerOnly); }
        public Component enabled() { return miniMessage().deserialize(config().enabled); }
        public Component disabled() { return miniMessage().deserialize(config().disabled); }

    }

    public record Report(ServerPlatform platform, MessageConfig.Report config, MiniMessage miniMessage) {

        public Component serverFromServer(CommonCommandSender reporter, CommonPlayer reportedPlayer, String message) {
            return miniMessage().deserialize(
                platform.isProxy()
                    ? config().proxyFromProxy
                    : config().serverFromServer,
                Placeholders.sender("reporter", reporter),
                Placeholders.sender("reported_player", reportedPlayer),
                Placeholder.unparsed("reason", message)
            );
        }

        public String discordFromServer(CommonCommandSender reporter, CommonPlayer reportedPlayer, String reason) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage.deserialize(
                    platform.isProxy()
                        ? config().discordFromProxy
                        : config().discordFromServer,
                    Placeholders.sender("reporter", reporter),
                    Placeholders.sender("reported_player", reportedPlayer),
                    Placeholder.unparsed("reason", reason)
                )
            );
        }

        public Component success(CommonPlayer reportedPlayer) {
            return miniMessage().deserialize(
                config().success,
                Placeholders.sender(reportedPlayer)
            );
        }

        public Component playerNotFound(String input) {
            return miniMessage().deserialize(
                config().playerNotFound,
                Placeholder.unparsed("input", input)
            );
        }

        public Component usage() {
            return miniMessage().deserialize(config().usage);
        }

    }

    public record HelpOp(ServerPlatform platform, MessageConfig.HelpOp config, MiniMessage miniMessage) {

        public Component serverFromServer(CommonCommandSender sender, String message) {
            return miniMessage().deserialize(
                platform.isProxy()
                    ? config().proxyFromProxy
                    : config().serverFromServer,
                Placeholders.sender(sender),
                Placeholder.unparsed("message", message)
            );
        }

        public String discordFromServer(CommonCommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage.deserialize(
                    platform.isProxy()
                        ? config().discordFromProxy
                        : config().discordFromServer,
                    Placeholders.sender(sender),
                    Placeholder.unparsed("message", message)
                )
            );
        }

        public Component success() {
            return miniMessage().deserialize(config().success);
        }

        public Component usage() {
            return miniMessage().deserialize(config().usage);
        }

    }

}
