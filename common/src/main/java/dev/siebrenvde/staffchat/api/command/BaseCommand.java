package dev.siebrenvde.staffchat.api.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.messages.Messages;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseCommand {

    private final String name;
    @Nullable private final String[] aliases;
    @Nullable private final String description;
    @Nullable private final String rootPermission;

    /**
     * Creates a new command with the provided parameters
     * @param name the name of the command
     * @param aliases the aliases of the command
     * @param description the description of the command
     * @param rootPermission the root permission of the command
     */
    public BaseCommand(String name, @Nullable String[] aliases, @Nullable String description, @Nullable String rootPermission) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
        this.rootPermission = rootPermission;
    }

    /**
     * The Brigadier implementation of the command
     * @param manager the Brigadier command manager
     * @return a new LiteralCommandNode
     * @param <C> the server software's command sender
     */
    public abstract <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager);

    /**
     * The simple implementation of the command
     * @param sender the command sender
     * @param args the command arguments
     */
    public abstract void simple(CommonCommandSender sender, String[] args);

    /**
     * The suggestions for the simple command
     * @param sender the command sender
     * @param args the command arguments
     * @return a list of strings to suggest
     */
    public List<String> suggestions(CommonCommandSender sender, String[] args) {
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
     * {@return the root permission of the command}
     */
    public @Nullable String getRootPermission() { return rootPermission; }

    /**
     * Checks whether the command sender has the given permission
     * <br>
     * If {@code false}, sends them a permission message
     * @param sender the command sender
     * @param permission the permission to check
     * @return {@code true} if the command sender has the given permission
     */
    public boolean checkPermission(CommonCommandSender sender, String permission) {
        if(permission == null || sender.hasPermission(permission)) return true;
        sender.sendMessage(Messages.messages().permissionMessage());
        return false;
    }

}
