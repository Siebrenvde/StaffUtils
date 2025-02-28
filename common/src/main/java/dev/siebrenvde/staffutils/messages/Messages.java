package dev.siebrenvde.staffutils.messages;

import dev.siebrenvde.staffutils.api.player.Player;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.config.MessageConfig;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import net.dv8tion.jda.api.entities.Member;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static dev.siebrenvde.staffutils.StaffUtils.getPlatform;
import static net.kyori.adventure.text.Component.join;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

/**
 * Parses MiniMessage messages from the config
 */
@NullMarked
public class Messages {

    private final MessageConfig config;

    @Nullable private static Messages instance;
    private final MsgCommands commands;
    private final StaffChat staffChat;
    private final Report report;
    private final HelpOp helpOp;
    private final MsgStaffUtils staffUtils;

    public Messages() {
        instance = this;
        config = Config.messages();
        commands = new MsgCommands(config.commands);
        staffChat = new StaffChat(config.staffChat);
        report = new Report(config.report);
        helpOp = new HelpOp(config.helpOp);
        staffUtils = new MsgStaffUtils(config.staffUtils);
    }

    public static Messages messages() { return Objects.requireNonNull(instance); }
    public static MsgCommands commands() { return messages().commands; }
    public static StaffChat staffChat() { return messages().staffChat; }
    public static Report report() { return messages().report; }
    public static HelpOp helpOp() { return messages().helpOp; }
    public static MsgStaffUtils staffUtils() { return messages().staffUtils; }

    public record MsgCommands(MessageConfig.MsgCommands config) {

        public Component permissionMessage() {
            return miniMessage().deserialize(config.permissionMessage.getRealValue());
        }

        public Component usage(String usage) {
            return miniMessage().deserialize(
                config.usage.getRealValue(),
                Placeholder.unparsed("usage", usage)
            );
        }

        public Component multilineUsage(List<String> usages) {
            return miniMessage().deserialize(
                config.multilineUsage.getRealValue(),
                Placeholder.component("usage", join(
                    JoinConfiguration.newlines(),
                    usages.stream().map(Component::text).toList()
                ))
            );
        }

    }

    public record StaffChat(MessageConfig.StaffChat config) {

        /**
         * The message sent in-game when using the staffchat command
         * @param sender the message sender
         * @param message the message
         * @return the message parsed as a Component
         */
        public Component serverFromServer(CommandSender sender, String message) {
            return miniMessage().deserialize(
                getPlatform().isProxy()
                    ? config().proxyFromProxy.getRealValue()
                    : config().serverFromServer.getRealValue(),
                Placeholders.sender(sender),
                Config.config().staffChat.allowMiniMessage.getRealValue()
                    ? Placeholders.formattedMessage(message)
                    : Placeholder.unparsed("message", message)
            );
        }

        public Component serverFromDiscord(Member author, String message) {
            return miniMessage().deserialize(
                config().gameFromDiscord.getRealValue(),
                Placeholders.discordMember(author),
                Config.config().staffChat.allowMiniMessage.getRealValue()
                    ? Placeholders.formattedMessage(message)
                    : Placeholder.unparsed("message", message)
            );
        }

        public String discordFromServer(CommandSender sender, String message) {
            return PlainTextComponentSerializer.plainText().serialize(
                miniMessage().deserialize(
                    getPlatform().isProxy()
                        ? config().discordFromProxy.getRealValue()
                        : config().discordFromServer.getRealValue(),
                    Placeholders.sender(sender),
                    Config.config().staffChat.allowMiniMessage.getRealValue()
                        ? Placeholders.formattedMessage(message)
                        : Placeholder.unparsed("message", message)
                )
            );
        }

        public Component playerOnly() { return miniMessage().deserialize(config().playerOnly.getRealValue()); }
        public Component enabled() { return miniMessage().deserialize(config().enabled.getRealValue()); }
        public Component disabled() { return miniMessage().deserialize(config().disabled.getRealValue()); }
        public Component signedToggleFail() { return miniMessage().deserialize(config().signedToggleFail.getRealValue()); }

    }

    public record Report(MessageConfig.Report config) {

        public Component serverFromServer(CommandSender reporter, Player reportedPlayer, String message) {
            return miniMessage().deserialize(
                getPlatform().isProxy()
                    ? config().proxyFromProxy.getRealValue()
                    : config().serverFromServer.getRealValue(),
                Placeholders.sender("reporter", reporter),
                Placeholders.sender("reported_player", reportedPlayer),
                Placeholder.unparsed("reason", message)
            );
        }

        public String discordFromServer(CommandSender reporter, Player reportedPlayer, String reason) {
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

        public Component success(Player reportedPlayer) {
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

    }

    public record HelpOp(MessageConfig.HelpOp config) {

        public Component serverFromServer(CommandSender sender, String message) {
            return miniMessage().deserialize(
                getPlatform().isProxy()
                    ? config().proxyFromProxy.getRealValue()
                    : config().serverFromServer.getRealValue(),
                Placeholders.sender(sender),
                Placeholder.unparsed("message", message)
            );
        }

        public String discordFromServer(CommandSender sender, String message) {
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

    }

    public record MsgStaffUtils(MessageConfig.MsgStaffUtils config) {

        public Component reloadedConfigs() {
            return miniMessage().deserialize(config().reloadedConfigs.getRealValue());
        }

    }

}
