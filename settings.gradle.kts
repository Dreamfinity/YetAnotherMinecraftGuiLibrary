pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        exclusiveContent {
            forRepository {
                maven(url = "https://maven.minecraftforge.net/") {
                    name = "forge"
                }
            }
            filter {
                includeGroupByRegex("net\\.minecraftforge.*")
                includeGroupByRegex("de\\.oceanlabs.*")
            }
        }
    }
    resolutionStrategy {
        eachPlugin {
            if(requested.id.toString() == "forge") {
                useModule("com.anatawa12.forge:ForgeGradle:${ requested.version }")
            }
        }
    }
}

rootProject.name = "yamgl"

include("core")
include("platforms:1-7-10-forge")
include("platforms:1-12-2-forge")