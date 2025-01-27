package dev.siebrenvde.staffutils.addons;

import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.api.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;

import java.util.Optional;

public class LuckPermsAddon {

    private static LuckPermsAddon instance;
    private LuckPerms api = null;

    public LuckPermsAddon() {
        instance = this;
        try {
            api = LuckPermsProvider.get();
            StaffUtils.LOGGER.optional("Loaded LuckPermsAddon");
        } catch(IllegalStateException e) {
            StaffUtils.LOGGER.error("LuckPerms was found but failed to load addon");
            StaffUtils.LOGGER.error(e.getMessage());
        } catch(NoClassDefFoundError ignored) {
            StaffUtils.LOGGER.optional("LuckPerms was not found, addon will not be loaded");
        }
    }

    private Optional<User> getUser(Player player) {
        return Optional.ofNullable(api.getUserManager().getUser(player.getUniqueId()));
    }

    public Optional<Group> getPlayerGroup(Player player) {
        return getUser(player).map(value -> api.getGroupManager().getGroup(value.getPrimaryGroup()));
    }

    public Optional<Component> getPlayerPrefix(Player player) {
        return getUser(player)
            .map(user -> user.getCachedData().getMetaData().getPrefix())
            .map(prefix -> LegacyComponentSerializer.legacyAmpersand().deserialize(prefix));
    }

    public Optional<Component> getPlayerSuffix(Player player) {
        return getUser(player)
            .map(user -> user.getCachedData().getMetaData().getSuffix())
            .map(suffix -> LegacyComponentSerializer.legacyAmpersand().deserialize(suffix));
    }

    public static LuckPermsAddon get() { return instance; }
    public boolean isLoaded() { return api != null; }

}
