package dev.siebrenvde.staffchat.addons;

import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
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
            StaffChat.LOGGER.optional("Loaded LuckPermsAddon");
        } catch(IllegalStateException e) {
            StaffChat.LOGGER.error("LuckPerms was found but failed to load addon");
            StaffChat.LOGGER.error(e.getMessage());
        } catch(NoClassDefFoundError ignored) {
            StaffChat.LOGGER.optional("LuckPerms was not found, addon will not be loaded");
        }
    }

    private Optional<User> getUser(CommonPlayer player) {
        return Optional.ofNullable(api.getUserManager().getUser(player.getUniqueId()));
    }

    public Optional<Group> getPlayerGroup(CommonPlayer player) {
        return getUser(player).map(value -> api.getGroupManager().getGroup(value.getPrimaryGroup()));
    }

    public Optional<Component> getPlayerPrefix(CommonPlayer player) {
        return getUser(player)
            .map(user -> user.getCachedData().getMetaData().getPrefix())
            .map(prefix -> LegacyComponentSerializer.legacyAmpersand().deserialize(prefix));
    }

    public Optional<Component> getPlayerSuffix(CommonPlayer player) {
        return getUser(player)
            .map(user -> user.getCachedData().getMetaData().getSuffix())
            .map(suffix -> LegacyComponentSerializer.legacyAmpersand().deserialize(suffix));
    }

    public static LuckPermsAddon get() { return instance; }
    public boolean isLoaded() { return api != null; }

}
