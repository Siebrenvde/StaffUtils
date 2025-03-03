plugins {
    id("net.kyori.indra.git") version "3.1.3"
    id("net.kyori.blossom") version "2.1.0"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.10"
}

dependencies {
    implementation("dev.siebrenvde:ConfigLib:0.2.0")
    compileOnly("com.github.Spicord.Spicord:spicord-common:5.6.0")
    compileOnly("net.dv8tion:JDA:5.0.1")
    compileOnly("com.mojang:brigadier:1.0.18")
    compileOnly("net.luckperms:api:5.4")
    implementation("net.kyori:adventure-api:4.18.0")
    implementation("net.kyori:adventure-text-minimessage:4.18.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.18.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.18.0")
    implementation("org.jspecify:jspecify:1.0.0")
}

sourceSets.main {
    blossom.javaSources {
        property("version", project.version.toString())
        property("gitCommit", indraGit.commit()?.name())
        property("gitBranch", indraGit.branchName())
    }
}
