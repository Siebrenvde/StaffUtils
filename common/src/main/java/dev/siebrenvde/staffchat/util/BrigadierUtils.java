package dev.siebrenvde.staffchat.util;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;

import java.util.function.Predicate;

public class BrigadierUtils {

    public static <C> Predicate<C> hasPermission(String permission) {
        return source -> CommonCommandSender.of(source).hasPermission(permission);
    }

    public static <C> Command<C> withSender(SenderCommand<C> command) {
        return ctx -> {
            command.run(ctx, CommonCommandSender.of(ctx.getSource()));
            return Command.SINGLE_SUCCESS;
        };
    }

    @FunctionalInterface
    public interface SenderCommand<C> {
        void run(CommandContext<C> context, CommonCommandSender sender) throws CommandSyntaxException;
    }

}
