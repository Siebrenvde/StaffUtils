package dev.siebrenvde.staffutils.paper.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.siebrenvde.staffutils.api.command.argument.arguments.PlayerArgument;
import dev.siebrenvde.staffutils.api.player.Player;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
@NullMarked
public class PaperPlayerArgument implements PlayerArgument {

    @Override
    public ArgumentType<?> getType() {
        return ArgumentTypes.player();
    }

    @Override
    public <C> @Nullable Player resolve(CommandContext<C> ctx, String name) throws CommandSyntaxException {
        return Player.of(ctx.getArgument(name, PlayerSelectorArgumentResolver.class)
            .resolve((CommandSourceStack) ctx.getSource())
            .getFirst()
        );

    }

}
