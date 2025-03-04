plugins {
    id("staffutils.implementation-conventions")
    alias(libs.plugins.resource.factory.bukkit)
}

dependencies {
    implementation(project(":common"))
    compileOnly(libs.spigot)
    compileOnly(libs.brigadier)
    implementation(libs.bundles.adventure)
    implementation(libs.adventure.bukkit)
    implementation(libs.bstats.bukkit)
    compileOnly(libs.jspecify)
}

tasks.shadowJar {
    relocate("net.kyori", "dev.siebrenvde.staffutils.libs.kyori")
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
