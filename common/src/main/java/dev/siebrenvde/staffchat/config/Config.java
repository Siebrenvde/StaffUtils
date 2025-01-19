package dev.siebrenvde.staffchat.config;

import dev.siebrenvde.configlib.ConfigLib;

import java.nio.file.Path;

public class Config {

    public static MainConfig CONFIG;
    public static MessageConfig MESSAGES;
    public static CommandConfig COMMANDS;

    public static void load(Path path) {
        CONFIG = ConfigLib.toml(path, "config", MainConfig.class);
        MESSAGES = ConfigLib.toml(path, "messages", MessageConfig.class);
        COMMANDS = ConfigLib.toml(path, "commands", CommandConfig.class);
    }

}
