plugins {
    id("java")
    id("net.kyori.indra")
}

indra {
    javaVersions {
        target(21)
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype"
    }
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
    maven("https://jitpack.io/") {
        name = "jitpack"
    }
    maven("https://files.siebrenvde.dev/repository/releases/") {
        name = "siebrenvde"
    }
}
