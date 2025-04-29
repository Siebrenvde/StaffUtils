package dev.siebrenvde.staffutils.config;

import dev.siebrenvde.staffutils.config.annotations.RequireProxy;
import dev.siebrenvde.staffutils.config.annotations.WordString;
import org.jspecify.annotations.NullMarked;
import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.ChangeWarning;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.annotations.DisplayName;
import org.quiltmc.config.api.annotations.DisplayNameConvention;
import org.quiltmc.config.api.annotations.SerializedName;
import org.quiltmc.config.api.annotations.SerializedNameConvention;
import org.quiltmc.config.api.metadata.NamingSchemes;
import org.quiltmc.config.api.values.TrackedValue;

import static org.quiltmc.config.api.metadata.ChangeWarning.Type.Custom;

@DisplayNameConvention(NamingSchemes.TITLE_CASE)
@SerializedNameConvention(NamingSchemes.SNAKE_CASE)
@NullMarked
public final class MainConfig extends ReflectiveConfig {

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
