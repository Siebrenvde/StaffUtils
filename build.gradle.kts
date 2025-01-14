import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.2"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":spigot"))
    implementation(project(":bungeecord"))
    implementation(project(":velocity"))
}

allprojects {
    apply(plugin = "java")

    group = "dev.siebrenvde"
    version = "2.0.0-SNAPSHOT"

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
    }
}

subprojects {
    val targetJavaVersion = 21
    java {
        val javaVersion = JavaVersion.toVersion(targetJavaVersion)
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        if (JavaVersion.current() < javaVersion) {
            toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"

        if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
            options.release.set(targetJavaVersion)
        }
    }
}

tasks.withType(ShadowJar::class) {
    archiveClassifier.set("")

    // Relocate dependencies
    // relocate("net.kyori", "dev.siebrenvde.staffchat.libs.kyori") - Breaks Velocity
    relocate("com.electronwill.nightconfig", "dev.siebrenvde.staffchat.libs.nightconfig")
}
