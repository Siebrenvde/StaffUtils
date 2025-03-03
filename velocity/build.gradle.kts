plugins {
    id("staffutils.common-conventions")
    alias(libs.plugins.resource.factory.velocity)
}

dependencies {
    implementation(project(":common"))
    compileOnly(libs.velocity)
    implementation(libs.bstats.velocity)
}

velocityPluginJson {
    main = "dev.siebrenvde.staffutils.velocity.StaffUtilsVelocity"
    id = rootProject.name.lowercase()
    name = rootProject.name
    //description = project.description
    url = "https://github.com/Siebrenvde/StaffUtils"
    authors.add("Siebrenvde")
    dependency("spicord")
    dependency("luckperms", true)
    dependency("signedvelocity", true)
}
