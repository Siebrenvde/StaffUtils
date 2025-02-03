plugins {
    id("xyz.jpenilla.resource-factory-bungee-convention") version "1.2.0"
}

dependencies {
    implementation(project(":common"))
    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bungeecord:4.3.4")
    implementation("org.bstats:bstats-bungeecord:3.1.0")
}

bungeePluginYaml {
    main = "dev.siebrenvde.staffutils.bungeecord.StaffUtilsBungee"
    name = rootProject.name
    //description = project.description
    author = "Siebrenvde"
    depends.add("Spicord")
    softDepends.add("LuckPerms")
}
