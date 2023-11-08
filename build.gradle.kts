import java.io.FileInputStream
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "forge"
            url = uri("https://files.minecraftforge.net/maven")
        }
        maven {
            url = uri("https://cloudrep.veritaris.me/repos")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.anatawa12.forge:ForgeGradle:1.2-1.0.+") {
            isChanging = true
        }
    }
}

plugins {
    kotlin("jvm") version "1.7.10" apply false
    `kotlin-dsl`
    java
}

val projectBuildPropertiesFile = "build.properties"
val projectBuildProperties = Properties().apply {
    load(FileInputStream(File(projectBuildPropertiesFile)))
}
project.version = projectBuildProperties.getProperty("version")
project.extra["buildVersion"] = projectBuildProperties.getProperty("buildVersion", "0")

allprojects {
    group = "org.dreamfinity.${rootProject.name}"
    version = project.version
    repositories {
        maven {
            url = uri("https://cloudrep.veritaris.me/repos/")
            metadataSources {
                mavenPom()
                artifact()
            }
        }
        flatDir {
            dirs("libs")
        }
        mavenCentral()
    }
}

subprojects.forEach { _ ->
    apply(plugin = "java")
    apply(plugin = "idea")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")
}

val semanticVersioning = mapOf(
    "Major" to "Builds .jar increasing major number: major.y.z",
    "Minor" to "Builds .jar increasing minor number: x.minor.y",
    "Patch" to "Builds .jar increasing patch number: x.y.patch",
    "JustBuild" to "Builds .jar adding \"-build-N\" suffix and increasing build number: x.y.z-build-N",
)

semanticVersioning.forEach { (k, _) ->
    tasks.register<WriteProperties>(k) {
        outputFile = file(projectBuildPropertiesFile)
        projectBuildProperties.replace(
            "buildVersion",
            if (k.toLowerCase() == "justbuild") "${Integer.parseInt(projectBuildProperties.getProperty("buildVersion") as String) + 1}" else "0"
        )
        projectBuildProperties.replace("version", "0.1.0")
        project.version = makeVersion(k)
        project.extra["buildVersion"] = "0"
        this.group = "Semantic versioned"
        this.finalizedBy("build")
    }
}

tasks.register<GradleBuild>("TestSemVerBuilds") {
    tasks = mutableListOf("JustBuild", "Patch", "Minor", "Major")
}

fun makeVersion(bumpType: String): String {
    val prevVersion: String = project.version as String
    var (major, minor, patch) = prevVersion.split(".")
    println("Old version: ${prevVersion}, old build number: ${project.extra["buildVersion"].toString()}")
    patch = patch.split("-")[0]

    val newVersion = when (bumpType.toLowerCase()) {
        "major" -> "${Integer.parseInt(major) + 1}.0.0"
        "minor" -> "${major}.${Integer.parseInt(minor) + 1}.0"
        "patch" -> "${major}.${minor}.${Integer.parseInt(patch) + 1}"
        else -> "${major}.${minor}.${patch}-build-${Integer.parseInt(project.extra["buildVersion"].toString()) + 1}"
    }

    if (bumpType in arrayOf("major", "minor", "patch")) {
        println("Migrating from $prevVersion to $newVersion")
    } else {
        println("Building version $newVersion")
    }
    project.version = "0.1.0"
    return newVersion
}