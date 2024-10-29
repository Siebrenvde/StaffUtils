package dev.siebrenvde.staffchat.common.config;

import java.nio.file.Path;

public class Config {

    public static MainConfig CONFIG;
    public static MessageConfig MESSAGES;
    public static CommandConfig COMMANDS;

    public static void load(Path path) {
        CONFIG = MainConfig.load(path);
        MESSAGES = MessageConfig.load(path);
        COMMANDS = CommandConfig.load(path);
    }

}
