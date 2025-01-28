package dev.siebrenvde.staffutils.config;

import dev.siebrenvde.configlib.ConfigLib;
import dev.siebrenvde.configlib.libs.quilt.config.api.ReflectiveConfig;
import dev.siebrenvde.configlib.libs.quilt.config.impl.ConfigFieldAnnotationProcessors;
import dev.siebrenvde.configlib.serialisers.TomlSerialiser;
import dev.siebrenvde.staffutils.config.annotations.RequireNonProxy;
import dev.siebrenvde.staffutils.config.annotations.RequireProxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {

    private static Path configPath;

    static {
        ConfigFieldAnnotationProcessors.register(RequireProxy.class, new RequireProxy.Processor());
        ConfigFieldAnnotationProcessors.register(RequireNonProxy.class, new RequireNonProxy.Processor());
    }

    public static MainConfig CONFIG;
    public static MessageConfig MESSAGES;
    public static CommandConfig COMMANDS;

    public static void load(Path path) {
        configPath = path;
        CONFIG = ConfigLib.toml(path, "config", MainConfig.class);
        MESSAGES = ConfigLib.toml(path, "messages", MessageConfig.class);
        COMMANDS = ConfigLib.toml(path, "commands", CommandConfig.class);
    }

    public static void reload() {
        reloadConfig(CONFIG);
        reloadConfig(MESSAGES);
        reloadConfig(COMMANDS);
    }

    private static void reloadConfig(ReflectiveConfig config) {
        try {
            TomlSerialiser.INSTANCE.deserialize(
                config,
                Files.newInputStream(configPath.resolve(config.id() + ".toml"))
            );
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to reload config '%s.toml'", config.id()), e);
        }
    }

}
