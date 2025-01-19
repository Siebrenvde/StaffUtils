package dev.siebrenvde.staffchat.messages;

import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.config.Config;
import dev.siebrenvde.staffchat.config.MessageConfig;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import static dev.siebrenvde.staffchat.StaffChat.getPlatform;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

/**
 * Parses MiniMessage messages from the config
 */
public class Messages {

    private final MessageConfig config;

    private static Messages instance;
    private final StaffChat staffChat;
    private final Report report;
    private final HelpOp helpOp;

    public Messages() {
        instance = this;
        config = Config.MESSAGES;
        staffChat = new StaffChat(config.staffChat);
        report = new Report(config.report);
        helpOp = new HelpOp(config.helpOp);
    }

    public static Messages messages() { return instance; }
    public static StaffChat staffChat() { return instance.staffChat; }
    public static Report report() { return instance.report; }
    public static HelpOp helpOp() { return instance.helpOp; }

    public Component permissionMessage() {
        return miniMessage().deserialize(config.permissionMessage.getRealValue());
    }

    public record StaffChat(MessageConfig.StaffChat config) {

        /**
         * The message sent in-game when using the staffchat command
         * @param sender the message sender
         * @param message the message
         * @return the message parsed as a Component
         */
        public Component serverFromServer(CommonCommandSender sender, String message) {
            return miniMessage().deserialize(
                getPlatform().isProxy()
                    ? config().proxyFromProxy.getRealValue()
                    : config().serverFromServer.getRealValue(),
                Placeholders.sender(sender),
                Placeholders.formattedMessage(message) // TODO: Add config option to disable parsing
            );
        }

        public Component serverFromDiscord(Member author, String message) {
            return miniMessage().deserialize(
                config().gameFromDiscord.getRealValue(),
                Placeholders.discordMember(author),
                Placeholders.formattedMessage(message)
            );
        }

        public String discordFromServer(CommonCommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage().deserialize(
                    getPlatform().isProxy()
                        ? config().discordFromProxy.getRealValue()
                        : config().discordFromServer.getRealValue(),
                    Placeholders.sender(sender),
                    Placeholders.formattedMessage(message)
                )
            );
        }

        public Component playerOnly() { return miniMessage().deserialize(config().playerOnly.getRealValue()); }
        public Component enabled() { return miniMessage().deserialize(config().enabled.getRealValue()); }
        public Component disabled() { return miniMessage().deserialize(config().disabled.getRealValue()); }

    }

    public record Report(MessageConfig.Report config) {

        public Component serverFromServer(CommonCommandSender reporter, CommonPlayer reportedPlayer, String message) {
            return miniMessage().deserialize(
                getPlatform().isProxy()
                    ? config().proxyFromProxy.getRealValue()
                    : config().serverFromServer.getRealValue(),
                Placeholders.sender("reporter", reporter),
                Placeholders.sender("reported_player", reportedPlayer),
                Placeholder.unparsed("reason", message)
            );
        }

        public String discordFromServer(CommonCommandSender reporter, CommonPlayer reportedPlayer, String reason) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage().deserialize(
                    getPlatform().isProxy()
                        ? config().discordFromProxy.getRealValue()
                        : config().discordFromServer.getRealValue(),
                    Placeholders.sender("reporter", reporter),
                    Placeholders.sender("reported_player", reportedPlayer),
                    Placeholder.unparsed("reason", reason)
                )
            );
        }

        public Component success(CommonPlayer reportedPlayer) {
            return miniMessage().deserialize(
                config().success.getRealValue(),
                Placeholders.sender(reportedPlayer)
            );
        }

        public Component playerNotFound(String input) {
            return miniMessage().deserialize(
                config().playerNotFound.getRealValue(),
                Placeholder.unparsed("input", input)
            );
        }

        public Component usage() {
            return miniMessage().deserialize(config().usage.getRealValue());
        }

    }

    public record HelpOp(MessageConfig.HelpOp config) {

        public Component serverFromServer(CommonCommandSender sender, String message) {
            return miniMessage().deserialize(
                getPlatform().isProxy()
                    ? config().proxyFromProxy.getRealValue()
                    : config().serverFromServer.getRealValue(),
                Placeholders.sender(sender),
                Placeholder.unparsed("message", message)
            );
        }

        public String discordFromServer(CommonCommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage().deserialize(
                    getPlatform().isProxy()
                        ? config().discordFromProxy.getRealValue()
                        : config().discordFromServer.getRealValue(),
                    Placeholders.sender(sender),
                    Placeholder.unparsed("message", message)
                )
            );
        }

        public Component success() {
            return miniMessage().deserialize(config().success.getRealValue());
        }

        public Component usage() {
            return miniMessage().deserialize(config().usage.getRealValue());
        }

    }

}
