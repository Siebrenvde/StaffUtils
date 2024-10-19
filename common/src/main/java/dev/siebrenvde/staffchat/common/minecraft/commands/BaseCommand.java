package dev.siebrenvde.staffchat.common.minecraft.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.common.minecraft.BrigadierCommandManager;
import dev.siebrenvde.staffchat.common.minecraft.CommandSender;
import dev.siebrenvde.staffchat.common.minecraft.ServerPlatform;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseCommand {

    private final String name;
    @Nullable private final String[] aliases;
    @Nullable private final String description;
    @Nullable private final String permission;

    /**
     * Creates a new command with the provided parameters
     * @param name the name of the command
     * @param aliases the aliases of the command
     * @param description the description of the command
     * @param permission the permission of the command
     */
    public BaseCommand(String name, @Nullable String[] aliases, @Nullable String description, @Nullable String permission) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
        this.permission = permission;
    }

    /**
     * The Brigadier implementation of the command
     * @param manager the Brigadier command manager
     * @param platform the server platform
     * @return a new LiteralCommandNode
     * @param <C> the server software's command sender
     */
    public abstract <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager, ServerPlatform platform);

    /**
     * The simple implementation of the command
     * @param sender the command sender
     * @param args the command arguments
     */
    public abstract void simple(CommandSender sender, String[] args);

    /**
     * The suggestions for the simple command
     * @param sender the command sender
     * @param args the command arguments
     * @return a list of strings to suggest
     */
    public List<String> suggestions(CommandSender sender, String[] args) {
        return List.of();
    }

    /**
     * {@return the name of the command}
     */
    public String getName() { return name; }

    /**
     * {@return the aliases of the command}
     */
    public String[] getAliases() { return aliases; }

    /**
     * {@return the description of the command}
     */
    public String getDescription() { return description != null ? description : ""; }

    /**
     * {@return the permission of the command}
     */
    public @Nullable String getPermission() { return permission; }

    /**
     * Returns whether the command sender has the command's permission
     * @param sender the command sender
     * @return {@code true} if the command sender has the command's permission
     */
    public boolean checkPermission(CommandSender sender) {
        if(sender.hasPermission(permission) || permission == null) return true;
        sender.sendMessage(Component.text("You don't have permission to execute this command!", NamedTextColor.RED));
        return false;
    }

}
