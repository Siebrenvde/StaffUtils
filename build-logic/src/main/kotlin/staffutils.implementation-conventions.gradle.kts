plugins {
    id("staffutils.common-conventions")
    id("com.gradleup.shadow")
}

tasks.shadowJar {
    archiveBaseName = rootProject.name.lowercase()
    archiveClassifier = project.name
    destinationDirectory = file("$rootDir/build/libs")

    relocate("org.bstats", "dev.siebrenvde.staffutils.libs.bstats")

    // ConfigLib
    relocate("org.quiltmc", "dev.siebrenvde.staffutils.libs.quilt")
    relocate("com.electronwill.nightconfig", "dev.siebrenvde.staffutils.libs.nightconfig")
    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "LICENSE" // Until I figure out how to rename this, it has to go
    )
}
