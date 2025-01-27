plugins {
    id("xyz.jpenilla.resource-factory-velocity-convention") version "1.2.0"
}

dependencies {
    implementation(project(":common"))
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
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
