plugins {
    id("staffutils.common-conventions")
    alias(libs.plugins.resource.factory.bukkit)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":paper"))
    compileOnly(libs.spigot)
    compileOnly(libs.brigadier)
    implementation(libs.adventure.bukkit)
    implementation(libs.bstats.bukkit)
    implementation(libs.jspecify)
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
