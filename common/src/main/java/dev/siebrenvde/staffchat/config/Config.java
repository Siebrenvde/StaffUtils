package dev.siebrenvde.staffchat.config;

import dev.siebrenvde.configlib.ConfigLib;
import dev.siebrenvde.configlib.libs.quilt.config.impl.ConfigFieldAnnotationProcessors;
import dev.siebrenvde.staffchat.config.annotations.RequireNonProxy;
import dev.siebrenvde.staffchat.config.annotations.RequireProxy;

import java.nio.file.Path;

public class Config {

    static {
        ConfigFieldAnnotationProcessors.register(RequireProxy.class, new RequireProxy.Processor());
        ConfigFieldAnnotationProcessors.register(RequireNonProxy.class, new RequireNonProxy.Processor());
    }

    public static MainConfig CONFIG;
    public static MessageConfig MESSAGES;
    public static CommandConfig COMMANDS;

    public static void load(Path path) {
        CONFIG = ConfigLib.toml(path, "config", MainConfig.class);
        MESSAGES = ConfigLib.toml(path, "messages", MessageConfig.class);
        COMMANDS = ConfigLib.toml(path, "commands", CommandConfig.class);
    }

}
