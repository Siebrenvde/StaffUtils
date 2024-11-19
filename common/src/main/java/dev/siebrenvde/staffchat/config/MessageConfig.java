package dev.siebrenvde.staffchat.config;

import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;
import dev.siebrenvde.staffchat.api.config.BaseConfig;

import java.nio.file.Path;

public class MessageConfig extends BaseConfig {

    public static MessageConfig load(Path path) {
        return ObjectDeserializer.standard().deserializeFields(load(path, "messages.toml"), MessageConfig::new);
    }

    @SerdeKey("permission_message")
    public String permissionMessage;

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
