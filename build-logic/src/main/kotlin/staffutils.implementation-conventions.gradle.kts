plugins {
    id("staffutils.common-conventions")
    id("com.gradleup.shadow")
}

tasks.shadowJar {
    archiveBaseName = rootProject.name.lowercase()
    archiveClassifier = project.name
    destinationDirectory = file("$rootDir/build/libs")

    relocate("org.bstats", "dev.siebrenvde.staffutils.libs.bstats")
}
