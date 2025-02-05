package dev.siebrenvde.staffutils.config;

import dev.siebrenvde.configlib.libs.quilt.config.api.ReflectiveConfig;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.SerializedName;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.TrackedValue;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.ValueList;
import dev.siebrenvde.configlib.metadata.NoOptionSpacing;

@NoOptionSpacing
public class CommandConfig extends ReflectiveConfig {

    @SerializedName("staffutils")
    public final Command staffUtils = new Command(
        true,
        "staffutils",
        new String[] {"su"},
        "The StaffUtils command"
    );
    @SerializedName("staffchat")
    public final Command staffChat = new Command(
        true,
        "staffchat",
        new String[] {"sc", "schat"},
        "Chat with other staff members"
    );
    public final Command report = new Command(
        true,
        "report",
        null,
        "Report a player"
    );
    @SerializedName("helpop")
    public final Command helpOp = new Command(
        true,
        "helpop",
        null,
        "Ask staff for help"
    );

    public static class Command extends Section {
        public final TrackedValue<Boolean> enabled;
        public final TrackedValue<String> name;
        public final TrackedValue<ValueList<String>> aliases;
        public final TrackedValue<String> description;

        public Command(boolean enabled, String name, String[] aliases, String description) {
            this.enabled = value(enabled);
            this.name = value(name);
            this.aliases = list("", aliases != null ? aliases : new String[0]);
            this.description = value(description != null ? description : "");
        }
    }

}
