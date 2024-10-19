package dev.siebrenvde.staffchat.common.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {

    public static Config load(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path file = path.resolve("config.toml");

        if(Files.notExists(file)) {
            try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.toml")) {
                if(in != null) Files.copy(in, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileConfig fileConfig = FileConfig.of(file);
        fileConfig.load();

        return ObjectDeserializer.standard().deserializeFields(fileConfig, Config::new);
    }

    @SerdeKey("config_version")
    public int configVersion;

    @SerdeKey("staff_channel")
    public String staffChannel;

    public Messages messages;
    public static class Messages {

        @SerdeKey("staffchat")
        public StaffChat staffChat;
        public static class StaffChat {

            @SerdeKey("server_from_server")
            public String serverFromServer;

            @SerdeKey("proxy_from_proxy")
            public String proxyFromProxy;

            @SerdeKey("game_from_discord")
            public String gameFromDiscord;

            @SerdeKey("discord_from_server")
            public String discordFromServer;

            @SerdeKey("discord_from_proxy")
            public String discordFromProxy;

        }

    }

}
