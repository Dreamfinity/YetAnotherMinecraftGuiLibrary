import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    kotlin("jvm")
    id("forge") version "1.2-1.1.+"
    idea
}

archivesName.set("yamgl")

minecraft {
    version = "1.7.10-10.13.4.1614-1.7.10"
    mappings = "stable_12"
}

dependencies {
    implementation(project(":core"))
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

tasks.jar {
    archiveClassifier.set("1.7.10-forge")
}