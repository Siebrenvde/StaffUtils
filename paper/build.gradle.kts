plugins {
    id("staffutils.common-conventions")
}

dependencies {
    implementation(project(":common"))
    compileOnly(libs.paper)
}
