plugins {
    id("staffutils.common-conventions")
    alias(libs.plugins.indra.git)
    alias(libs.plugins.blossom)
    alias(libs.plugins.idea.ext)
}

dependencies {
    implementation(libs.configlib)
    compileOnly(libs.jspecify)
    compileOnly(libs.brigadier)
    compileOnly(libs.jda)

    compileOnly(libs.bundles.adventure)

    compileOnly(libs.luckperms)
    compileOnly(libs.spicord)
}

sourceSets.main {
    blossom.javaSources {
        property("version", project.version.toString())
        property("gitCommit", indraGit.commit()?.name())
        property("gitBranch", indraGit.branchName())
    }
}
