package dev.siebrenvde.staffutils.config;

import dev.siebrenvde.configlib.libs.quilt.config.api.ReflectiveConfig;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.*;
import dev.siebrenvde.configlib.libs.quilt.config.api.metadata.NamingSchemes;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.TrackedValue;
import dev.siebrenvde.staffutils.config.annotations.RequireProxy;
import org.jspecify.annotations.NullMarked;
import dev.siebrenvde.staffutils.config.annotations.WordString;

import static dev.siebrenvde.configlib.libs.quilt.config.api.metadata.ChangeWarning.Type.Custom;

@DisplayNameConvention(NamingSchemes.TITLE_CASE)
@SerializedNameConvention(NamingSchemes.SNAKE_CASE)
@NullMarked
public class MainConfig extends ReflectiveConfig {

    @Comment("The id of the staff channel")
    @WordString
    @ChangeWarning(
        value = Custom,
        customMessage = "" // TODO: Add custom message
    )
    public final TrackedValue<String> staffChannel = value("");

    @Comment("For debug purposes")
    public final TrackedValue<Boolean> verboseLogging = value(false);

    @Comment("Allow commands that take a player to use players on the entire proxy")
    @RequireProxy
    public final TrackedValue<Boolean> allowGlobalPlayerCommands = value(false);

    @SerializedName("staffchat")
    public final ConfStaffChat staffChat = new ConfStaffChat();

    public static final class ConfStaffChat extends Section {

        @Comment("Allow the use of MiniMessage in staff chat messages")
        @DisplayName("Allow MiniMessage")
        @SerializedName("allow_minimessage")
        public final TrackedValue<Boolean> allowMiniMessage = value(true);

    }

}
