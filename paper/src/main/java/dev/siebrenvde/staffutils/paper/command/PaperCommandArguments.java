package dev.siebrenvde.staffutils.paper.command;

import dev.siebrenvde.staffutils.api.command.argument.arguments.PlayerArgument;
import dev.siebrenvde.staffutils.argument.CommandArgumentsImpl;
import dev.siebrenvde.staffutils.paper.command.arguments.PaperPlayerArgument;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PaperCommandArguments extends CommandArgumentsImpl {

    private final PaperPlayerArgument playerArgument;

    public PaperCommandArguments() {
        super();
        playerArgument = new PaperPlayerArgument();
    }

    @Override
    public PlayerArgument playerArgument() {
        return playerArgument;
    }

}
