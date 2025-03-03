import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("staffutils.common-conventions")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":spigot"))
    implementation(project(":bungeecord"))
    implementation(project(":velocity"))
}

tasks.withType(ShadowJar::class) {
    archiveClassifier.set("")

    // Relocate dependencies
    val libsPackage = "dev.siebrenvde.staffutils.libs"
    // relocateLib("net.kyori", "$libsPackage.kyori") - Breaks Velocity
    relocate("org.bstats", "$libsPackage.bstats")
}
