package dev.siebrenvde.staffutils.api.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.siebrenvde.staffutils.config.CommandConfig;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public abstract class BaseCommand {

    private final boolean enabled;
    private final String name;
    private final String[] aliases;
    private final String description;
    @Nullable private final String rootPermission;

    /**
     * Creates a new command with the provided parameters
     * @param name the name of the command
     * @param aliases the aliases of the command
     * @param description the description of the command
     * @param rootPermission the root permission of the command
     */
    public BaseCommand(String name, String @Nullable[] aliases, @Nullable String description, @Nullable String rootPermission) {
        enabled = true;
        this.name = name;
        this.aliases = aliases != null ? aliases : new String[0];
        this.description = description != null ? description : "";
        this.rootPermission = rootPermission;
    }

    /**
     * Creates a new command from a config file
     * @param config the command config
     * @param rootPermission the root permission of the command
     */
    public BaseCommand(CommandConfig.Command config, @Nullable String rootPermission) {
        this.enabled = config.enabled.getRealValue();
        this.name = config.name.getRealValue();
        this.aliases = config.aliases.getRealValue().toArray(new String[0]);
        this.description = config.description.getRealValue();
        this.rootPermission = rootPermission;
    }

    /**
     * The Brigadier implementation of the command
     * @param manager the Brigadier command manager
     * @return a new LiteralArgumentBuilder
     * @param <C> the server software's command sender
     */
    public abstract <C> LiteralArgumentBuilder<C> brigadier(CommandManager<C> manager);

    /**
     * {@return whether the command is enabled}
     */
    public boolean isEnabled() { return enabled; }

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
    public String getDescription() { return description; }

    /**
     * {@return the root permission of the command}
     */
    public @Nullable String getRootPermission() { return rootPermission; }

}
