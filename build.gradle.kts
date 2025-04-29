plugins {
    id("staffutils.common-conventions")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":spigot"))
    implementation(project(":bungeecord"))
    implementation(project(":velocity"))
}
