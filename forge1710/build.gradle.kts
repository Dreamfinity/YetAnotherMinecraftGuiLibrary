import net.minecraftforge.gradle.user.patch.UserPatchExtension

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "ossrh-snapshot"
        }
        maven(url = "https://files.minecraftforge.net/maven") {
            name = "forge"
        }
    }
    dependencies {
        classpath("com.anatawa12.forge:ForgeGradle:1.2-1.0.+") {
            isChanging = true
        }
    }
}

plugins {
    kotlin("jvm")
    java
}

apply(plugin = "forge")

val Project.minecraft: UserPatchExtension get() = (this as ExtensionAware).extensions.getByName("minecraft") as UserPatchExtension
fun Project.minecraft(configure: UserPatchExtension.() -> Unit): Unit = (this as ExtensionAware).extensions.configure("minecraft", configure)

minecraft {
    version = "1.7.10-10.13.4.1614-1.7.10"
    mappings = "stable_12"
    runDir = "run"
}
