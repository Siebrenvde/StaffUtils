plugins {
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":paper"))
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
}

bukkitPluginYaml {
    main = "dev.siebrenvde.staffchat.spigot.StaffChatSpigot"
    name = rootProject.name
    //description = project.description
    author = "Siebrenvde"
    apiVersion = "1.21"
    website = "https://github.com/Siebrenvde/StaffChat"
    depend.add("Spicord")
    softDepend.add("LuckPerms")
}
