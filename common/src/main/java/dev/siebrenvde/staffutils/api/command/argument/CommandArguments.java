package dev.siebrenvde.staffutils.api.command.argument;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.command.argument.arguments.PlayerArgument;

public interface CommandArguments {

    static CommandArguments arguments() {
        return StaffUtils.getCommandArguments();
    }

    PlayerArgument playerArgument();

}
