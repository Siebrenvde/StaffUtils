package dev.siebrenvde.staffutils.util;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.siebrenvde.staffutils.api.command.CommandSender;

import java.util.function.Predicate;

public class BrigadierUtils {

    public static <C> Predicate<C> hasPermission(String permission) {
        return source -> CommandSender.of(source).hasPermission(permission);
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
