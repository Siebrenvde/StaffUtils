package dev.siebrenvde.staffchat.config;

import dev.siebrenvde.configlib.ConfigComment;
import dev.siebrenvde.configlib.libs.quilt.config.api.ReflectiveConfig;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.Comment;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.SerializedName;
import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.SerializedNameConvention;
import dev.siebrenvde.configlib.libs.quilt.config.api.metadata.NamingSchemes;
import dev.siebrenvde.configlib.libs.quilt.config.api.values.TrackedValue;

@ConfigComment("All in-game messages are formatted using MiniMessage")
@ConfigComment("You can use the MiniMessage Viewer to preview and format your messages:")
@ConfigComment("https://webui.advntr.dev")
@SerializedNameConvention(NamingSchemes.SNAKE_CASE)
public final class MessageConfig extends ReflectiveConfig {

    @Comment("The message sent to players when they don't have permission to execute a command")
    public final TrackedValue<String> permissionMessage = value("<red>You don't have permission to execute this command</red>");

    @SerializedName("staffchat")
    public final StaffChat staffChat = new StaffChat();
    public final Report report = new Report();
    @SerializedName("helpop")
    public final HelpOp helpOp = new HelpOp();

    public final static class StaffChat extends Section {

        @Comment("The message shown to in-game staff when using the staffchat command")
        @Comment("Applies to: Paper & Spigot")
        @Comment("Placeholders:")
        @Comment("<displayname> - The player's displayname")
        @Comment("<username> - The player's username")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> serverFromServer = value("<red>StaffChat <yellow><username></yellow>:</red> <green><message></green>");

        @Comment("The message shown to in-game staff when using the staffchat command")
        @Comment("Applies to: Velocity & BungeeCord")
        @Comment("Placeholders:")
        @Comment("<username> - The player's username")
        @Comment("<server> - The server the player is connected to")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> proxyFromProxy = value("<red>StaffChat <yellow><username></yellow> <gold>(<server>)</gold>:</red> <green><message></green>");

        @Comment("The message shown to in-game staff when receiving a message from Discord")
        @Comment("Placeholders:")
        @Comment("<profile> - The user's (nick)name with extra info when hovering over their name")
        @Comment("<displayname> - The user's (nick)name in the current server")
        @Comment("<username> - The user's username")
        @Comment("<role> - The name of the user's top role")
        @Comment("<message> - The sent message")
        @Comment("Styling:")
        @Comment("<effective_colour> - The actual colour a member shows up as")
        @Comment("<role_colour> - The colour of the user's top role")
        public final TrackedValue<String> gameFromDiscord = value("<blue>Discord</blue> <red>StaffChat <yellow><profile></yellow>:</red> <green><message></green>");

        @Comment("The message shown in Discord when using the staffchat command")
        @Comment("Applies to: Paper & Spigot")
        @Comment("Placeholders:")
        @Comment("<displayname> - The player's displayname")
        @Comment("<username> - The player's username")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> discordFromServer = value("**[StaffChat] <username>**: <message>");

        @Comment("The message shown in Discord when using the staffchat command")

        @Comment("Placeholders:")
        @Comment("Applies to: Velocity & BungeeCord")
        @Comment("<username> - The player's username")
        @Comment("<server> - The server the player is connected to")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> discordFromProxy = value("**[StaffChat] <username> (<server>)**: <message>");

        @Comment("The message shown when a non-player tries to toggle StaffChat")
        public final TrackedValue<String> playerOnly = value("<red>Only players can toggle StaffChat</red>");

        @Comment("The message shown when a player enables StaffChat")
        public final TrackedValue<String> enabled = value("<green>Enabled StaffChat<newline>Any message you send will be sent to the staff chat</green>");

        @Comment("The message shown when a player disables StaffChat")
        public final TrackedValue<String> disabled = value("<red>Disabled StaffChat</red>");

    }

    public final static class Report extends Section {

        @Comment("The message shown to in-game staff when a player is reported")
        @Comment("Applies to: Paper & Spigot")
        @Comment("Placeholders:")
        @Comment("<reporter_displayname> - The reporter's displayname")
        @Comment("<reporter_username> - The reporter's username")
        @Comment("<reported_player_displayname> - The reported player's displayname")
        @Comment("<reported_player_username> - The reported player's username")
        @Comment("<reason> - The reason")
        @Comment("LuckPerms placeholders:")
        @Comment("<reporter_group_name> - The name of the reporter's primary group")
        @Comment("<reporter_group_displayname> - The display name of the reporter's primary group")
        @Comment("<reporter_prefix> - The reporter's prefix")
        @Comment("<reporter_suffix> - The reporter's suffix")
        @Comment("<reported_player_group_name> - The name of the reported player's primary group")
        @Comment("<reported_player_group_displayname> - The display name of the reported player's primary group")
        @Comment("<reported_player_prefix> - The reported player's prefix")
        @Comment("<reported_player_suffix> - The reported player's suffix")
        public final TrackedValue<String> serverFromServer = value("<red><reporter_username></red> reported <red><reported_player_username></red> for: <i><reason></i>");

        @Comment("The message shown to in-game staff when a player is reported")
        @Comment("Applies to: Velocity & BungeeCord")
        @Comment("Placeholders:")
        @Comment("<reporter_username> - The reporter's username")
        @Comment("<reporter_server> - The server the reporter is connected to")
        @Comment("<reported_player_username> - The reported player's username")
        @Comment("<reporter_server> - The server the reported player is connected to")
        @Comment("<reason> - The reason")
        @Comment("LuckPerms placeholders:")
        @Comment("<reporter_group_name> - The name of the reporter's primary group")
        @Comment("<reporter_group_displayname> - The display name of the reporter's primary group")
        @Comment("<reporter_prefix> - The reporter's prefix")
        @Comment("<reporter_suffix> - The reporter's suffix")
        @Comment("<reported_player_group_name> - The name of the reported player's primary group")
        @Comment("<reported_player_group_displayname> - The display name of the reported player's primary group")
        @Comment("<reported_player_prefix> - The reported player's prefix")
        @Comment("<reported_player_suffix> - The reported player's suffix")
        public final TrackedValue<String> proxyFromProxy = value("<red><reporter_username> (<reporter_server>)</red> reported <red><reported_player_username> (<reported_player_server>)</red> for: <i><reason></i>");

        @Comment("The message shown in Discord when a player is reported")
        @Comment("Applies to: Paper & Spigot")
        @Comment("Placeholders:")
        @Comment("<reporter_displayname> - The reporter's displayname")
        @Comment("<reporter_username> - The reporter's username")
        @Comment("<reported_player_displayname> - The reported player's displayname")
        @Comment("<reported_player_username> - The reported player's username")
        @Comment("<reason> - The reason")
        @Comment("LuckPerms placeholders:")
        @Comment("<reporter_group_name> - The name of the reporter's primary group")
        @Comment("<reporter_group_displayname> - The display name of the reporter's primary group")
        @Comment("<reporter_prefix> - The reporter's prefix")
        @Comment("<reporter_suffix> - The reporter's suffix")
        @Comment("<reported_player_group_name> - The name of the reported player's primary group")
        @Comment("<reported_player_group_displayname> - The display name of the reported player's primary group")
        @Comment("<reported_player_prefix> - The reported player's prefix")
        @Comment("<reported_player_suffix> - The reported player's suffix")
        public final TrackedValue<String> discordFromServer = value("**[Report] <reporter_username>** reported **<reported_player_username>** for: *<reason>*");

        @Comment("The message shown in Discord when a player is reported")
        @Comment("Applies to: Velocity & BungeeCord")
        @Comment("Placeholders:")
        @Comment("<reporter_username> - The reporter's username")
        @Comment("<reporter_server> - The server the reporter is connected to")
        @Comment("<reported_player_username> - The reported player's username")
        @Comment("<reporter_server> - The server the reported player is connected to")
        @Comment("<reason> - The reason")
        @Comment("LuckPerms placeholders:")
        @Comment("<reporter_group_name> - The name of the reporter's primary group")
        @Comment("<reporter_group_displayname> - The display name of the reporter's primary group")
        @Comment("<reporter_prefix> - The reporter's prefix")
        @Comment("<reporter_suffix> - The reporter's suffix")
        @Comment("<reported_player_group_name> - The name of the reported player's primary group")
        @Comment("<reported_player_group_displayname> - The display name of the reported player's primary group")
        @Comment("<reported_player_prefix> - The reported player's prefix")
        @Comment("<reported_player_suffix> - The reported player's suffix")
        public final TrackedValue<String> discordFromProxy = value("**[Report] <reporter_username> (<reporter_server>)** reported **<reported_player_username> (<reported_player_server>)** for: *<reason>*") ;

        @Comment("The message sent to the reporter after successfully reporting a player")
        @Comment("Placeholders:")
        @Comment("<displayname> - The reported player's displayname")
        @Comment("<username> - The reported player's username")
        public final TrackedValue<String> success = value("Successfully reported <red><username></red>");

        @Comment("The message sent to the reporter when the player was not found")
        @Comment("Placeholders:")
        @Comment("<input> - The name entered by the reporter")
        public final TrackedValue<String> playerNotFound = value("<red>Player '<input>' was not found</red>");

        @Comment("The message sent to the reporter when no player or reason is entered")
        public final TrackedValue<String> usage = value("<red>Usage: /report <player> <reason></red>");

    }

    public final static class HelpOp extends Section {

        @Comment("The message shown to in-game staff when the helpop command is used")
        @Comment("Applies to: Paper & Spigot")
        @Comment("Placeholders:")
        @Comment("<displayname> - The player's displayname")
        @Comment("<username> - The player's username")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> serverFromServer = value("<dark_red>HelpOp <yellow><username></yellow>:</dark_red> <green><message></green>");

        @Comment("The message shown to in-game staff when the helpop command is used")
        @Comment("Applies to: Velocity & BungeeCord")
        @Comment("Placeholders:")
        @Comment("<username> - The player's username")
        @Comment("<server> - The server the player is connected to")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> proxyFromProxy = value("<dark_red>HelpOp <yellow><username></yellow> <gold>(<server>)</gold>:</dark_red> <green><message></green>");

        @Comment("The message shown in Discord when the helpop command is used")
        @Comment("Applies to: Paper & Spigot")
        @Comment("Placeholders:")
        @Comment("<displayname> - The player's displayname")
        @Comment("<username> - The player's username")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> discordFromServer = value("**[HelpOp] <username>**: <message>");

        @Comment("The message shown in Discord when the helpop command is used")
        @Comment("Applies to: Velocity & BungeeCord")
        @Comment("Placeholders:")
        @Comment("<username> - The player's username")
        @Comment("<server> - The server the player is connected to")
        @Comment("<message> - The sent message")
        @Comment("LuckPerms placeholders:")
        @Comment("<group_name> - The name of the player's primary group")
        @Comment("<group_displayname> - The display name of the player's primary group")
        @Comment("<prefix> - The player's prefix")
        @Comment("<suffix> - The player's suffix")
        public final TrackedValue<String> discordFromProxy = value("**[HelpOp] <username> (<server>)**: <message>");

        @Comment("The message sent to the sender after successfully using the helpop command")
        public final TrackedValue<String> success = value("Successfully requested help");

        @Comment("The message sent to the sender when no message is entered")
        public final TrackedValue<String> usage = value("<red>Usage: /helpop <message></red>");

    }

}
