package dev.siebrenvde.staffchat.common.config;

import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;

public class Config {

    @SerdeKey("config_version")
    public int configVersion;

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
