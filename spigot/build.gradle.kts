dependencies {
    implementation(project(":common"))
    implementation(project(":paper"))
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
}

tasks.withType(ProcessResources::class) {
    filesMatching("plugin.yml") {
        expand("version" to version)
    }
}
