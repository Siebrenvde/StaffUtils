plugins {
    id("staffutils.implementation-conventions")
    alias(libs.plugins.resource.factory.bungee)
}

dependencies {
    implementation(project(":common"))
    compileOnly(libs.bungee)
    implementation(libs.bundles.adventure)
    implementation(libs.adventure.bungee)
    implementation(libs.bstats.bungee)
    compileOnly(libs.jspecify)
}

tasks.shadowJar {
    relocate("net.kyori", "dev.siebrenvde.staffutils.libs.kyori")
}

bungeePluginYaml {
    main = "dev.siebrenvde.staffutils.bungeecord.StaffUtilsBungee"
    name = rootProject.name
    //description = project.description
    author = "Siebrenvde"
    depends.add("Spicord")
    softDepends.add("LuckPerms")
}
