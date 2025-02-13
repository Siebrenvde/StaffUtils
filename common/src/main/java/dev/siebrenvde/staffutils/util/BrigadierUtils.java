package dev.siebrenvde.staffutils.util;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.siebrenvde.staffutils.api.command.CommandSender;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Predicate;

@NullMarked
public class BrigadierUtils {

    public static <C> Predicate<C> hasPermission(@Nullable String permission) {
        return source -> permission == null || CommandSender.of(source).hasPermission(permission);
    }

    public static <C> Command<C> withSender(SenderCommand<C> command) {
        return ctx -> {
            command.run(ctx, CommandSender.of(ctx.getSource()));
            return Command.SINGLE_SUCCESS;
        };
    }

    @FunctionalInterface
    public interface SenderCommand<C> {
        void run(CommandContext<C> context, CommandSender sender) throws CommandSyntaxException;
    }

}
