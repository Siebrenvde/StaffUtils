package dev.siebrenvde.staffutils.argument;

import dev.siebrenvde.staffutils.api.command.argument.CommandArguments;
import dev.siebrenvde.staffutils.api.command.argument.arguments.PlayerArgument;
import dev.siebrenvde.staffutils.argument.arguments.PlayerArgumentImpl;

public class CommandArgumentsImpl implements CommandArguments {

    private final PlayerArgument playerArgument;

    public CommandArgumentsImpl() {
        playerArgument = new PlayerArgumentImpl();
    }

    @Override
    public PlayerArgument playerArgument() {
        return playerArgument;
    }

}
