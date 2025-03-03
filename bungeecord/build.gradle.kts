plugins {
    id("staffutils.common-conventions")
    alias(libs.plugins.resource.factory.bungee)
}

dependencies {
    implementation(project(":common"))
    compileOnly(libs.bungee)
    implementation(libs.adventure.bungee)
    implementation(libs.bstats.bungee)
    implementation(libs.jspecify)
}

bungeePluginYaml {
    main = "dev.siebrenvde.staffutils.bungeecord.StaffUtilsBungee"
    name = rootProject.name
    //description = project.description
    author = "Siebrenvde"
    depends.add("Spicord")
    softDepends.add("LuckPerms")
}
