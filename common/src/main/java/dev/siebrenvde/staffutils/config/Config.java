package dev.siebrenvde.staffutils.config;

import dev.siebrenvde.configlib.ConfigLib;
import dev.siebrenvde.configlib.metadata.SkipWrite;
import dev.siebrenvde.configlib.serialisers.toml.TomlSerialiser;
import dev.siebrenvde.staffutils.StaffUtils;
import dev.siebrenvde.staffutils.config.annotations.RequireNonProxy;
import dev.siebrenvde.staffutils.config.annotations.RequireProxy;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.quiltmc.config.api.ReflectiveConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@NullMarked
public class Config {

    @Nullable private static Path configPath;

    static {
        SkipWrite.skipWhen(RequireProxy.class, !StaffUtils.getPlatform().isProxy());
        SkipWrite.skipWhen(RequireNonProxy.class, StaffUtils.getPlatform().isProxy());
    }

    @Nullable private static MainConfig CONFIG;
    @Nullable private static MessageConfig MESSAGES;
    @Nullable private static CommandConfig COMMANDS;

    public static void load(Path path) {
        configPath = path;
        CONFIG = ConfigLib.toml(path, "config", MainConfig.class);
        MESSAGES = ConfigLib.toml(path, "messages", MessageConfig.class);
        COMMANDS = ConfigLib.toml(path, "commands", CommandConfig.class);
    }

    public static void reload() {
        reloadConfig(config());
        reloadConfig(messages());
        reloadConfig(commands());
    }

    private static void reloadConfig(ReflectiveConfig config) {
        try {
            TomlSerialiser.INSTANCE.deserialize(
                config,
                Files.newInputStream(
                    Objects.requireNonNull(configPath).resolve(config.id() + ".toml")
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to reload config '%s.toml'", config.id()), e);
        }
    }

    public static MainConfig config() { return Objects.requireNonNull(CONFIG); }
    public static MessageConfig messages() { return Objects.requireNonNull(MESSAGES); }
    public static CommandConfig commands() { return Objects.requireNonNull(COMMANDS); }

}
