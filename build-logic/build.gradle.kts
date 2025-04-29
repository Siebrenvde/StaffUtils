plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.build.indra)
    implementation(libs.build.shadow)
}
