package dev.siebrenvde.staffutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.BaseCommand;
import dev.siebrenvde.staffutils.config.Config;
import dev.siebrenvde.staffutils.messages.Messages;
import dev.siebrenvde.staffutils.util.BuildParameters;
import dev.siebrenvde.staffutils.util.Permissions;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jspecify.annotations.NullMarked;

import static net.kyori.adventure.text.Component.text;

@NullMarked
public class StaffUtilsCommand<C> extends BaseCommand<C> {

    public StaffUtilsCommand() {
        super(Config.commands().staffUtils, Permissions.COMMAND_STAFFUTILS);
    }

    @Override
    public LiteralArgumentBuilder<C> builder() {
        return literal(getName())
            .requires(hasPermission(getRootPermission()))
            .then(literal("version")
                .executes(withSender((ctx, sender) -> {
                    sender.sendMessage(
                        text()
                            .append(text("StaffUtils "))
                            .append(text("("))
                            .append(StaffUtils.getPlatform().getServerType().displayName())
                            .append(text(")"))
                            .append(text(" version "))
                            .append(text(BuildParameters.VERSION, NamedTextColor.YELLOW))
                            .appendSpace()
                            .append(
                                text()
                                    .append(text("("))
                                    .append(text(BuildParameters.GIT_BRANCH, NamedTextColor.AQUA))
                                    .append(text("@"))
                                    .append(text(BuildParameters.GIT_COMMIT.substring(0, 7), NamedTextColor.GREEN))
                                    .append(text(")"))
                                    .clickEvent(ClickEvent.openUrl("https://github.com/Siebrenvde/StaffUtils/commit/" + BuildParameters.GIT_COMMIT))
                            )
                            .build()
                    );
                }))
            )
            .then(literal("reload")
                .requires(hasPermission(Permissions.COMMAND_STAFFUTILS_RELOAD))
                .executes(withSender((ctx, sender) -> {
                    Config.reload();
                    sender.sendMessage(Messages.staffUtils().reloadedConfigs());
                }))
            );
    }

}
