plugins {
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":paper"))
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("com.mojang:brigadier:1.0.18")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("org.jspecify:jspecify:1.0.0")
}

bukkitPluginYaml {
    main = "dev.siebrenvde.staffutils.spigot.StaffUtilsSpigot"
    name = rootProject.name
    //description = project.description
    author = "Siebrenvde"
    apiVersion = "1.21"
    website = "https://github.com/Siebrenvde/StaffUtils"
    depend.add("Spicord")
    softDepend.add("LuckPerms")
}
