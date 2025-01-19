package dev.siebrenvde.staffchat.config;

import dev.siebrenvde.configlib.NoOptionSpacing;
import dev.siebrenvde.configlib.libs.quilt.config.api.ReflectiveConfig;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.SerializedName;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.TrackedValue;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.ValueList;

@NoOptionSpacing
public class CommandConfig extends ReflectiveConfig {

    @SerializedName("staffchat")
    public final Command staffChat = new StaffChat();
    public final Command report = new Report();
    @SerializedName("helpop")
    public final Command helpOp = new HelpOp();

    private static class StaffChat extends Section implements Command {
        private final TrackedValue<Boolean> enabled = value(true);
        private final TrackedValue<String> name = value("staffchat");
        private final TrackedValue<ValueList<String>> aliases = list("", "sc", "staffc");
        private final TrackedValue<String> description = value("Chat with other staff members");

        @Override public TrackedValue<Boolean> enabled() { return enabled; }
        @Override public TrackedValue<String> name() { return name; }
        @Override public TrackedValue<ValueList<String>> aliases() { return aliases; }
        @Override public TrackedValue<String> description() { return description; }
    }

    private static class Report extends Section implements Command {
        private final TrackedValue<Boolean> enabled = value(true);
        private final TrackedValue<String> name = value("report");
        private final TrackedValue<ValueList<String>> aliases = list("");
        private final TrackedValue<String> description = value("Report a player");

        @Override public TrackedValue<Boolean> enabled() { return enabled; }
        @Override public TrackedValue<String> name() { return name; }
        @Override public TrackedValue<ValueList<String>> aliases() { return aliases; }
        @Override public TrackedValue<String> description() { return description; }
    }

    private static class HelpOp extends Section implements Command {
        private final TrackedValue<Boolean> enabled = value(true);
        private final TrackedValue<String> name = value("helpop");
        private final TrackedValue<ValueList<String>> aliases = list("");
        private final TrackedValue<String> description = value("Ask staff for help");

        @Override public TrackedValue<Boolean> enabled() { return enabled; }
        @Override public TrackedValue<String> name() { return name; }
        @Override public TrackedValue<ValueList<String>> aliases() { return aliases; }
        @Override public TrackedValue<String> description() { return description; }
    }

    public interface Command {
        TrackedValue<Boolean> enabled();
        TrackedValue<String> name();
        TrackedValue<ValueList<String>> aliases();
        TrackedValue<String> description();
    }

}
