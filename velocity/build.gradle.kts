dependencies {
    implementation(project(":common"))
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}

tasks.withType(ProcessResources::class) {
    filesMatching("velocity-plugin.json") {
        expand("version" to version)
    }
}
