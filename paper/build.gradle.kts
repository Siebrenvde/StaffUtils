plugins {
    id("staffutils.implementation-conventions")
    alias(libs.plugins.resource.factory.bukkit)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":spigot")) {
        exclude(group = "net.kyori")
    }
    compileOnly(libs.paper)
    implementation(libs.bstats.bukkit)
}

bukkitPluginYaml {
    main = "dev.siebrenvde.staffutils.paper.StaffUtilsPaper"
    name = rootProject.name
    //description = project.description
    author = "Siebrenvde"
    apiVersion = "1.21"
    website = "https://github.com/Siebrenvde/StaffUtils"
    depend.add("Spicord")
    softDepend.add("LuckPerms")
}
