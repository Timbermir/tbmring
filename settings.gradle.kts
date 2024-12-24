pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.8.0")
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    defaultLibrariesExtensionName = "root"
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
        google()
    }
//        create("androidConfig") {
//            from(files("gradle/android-config.versions.toml"))
//        }
//
//        create("gradleConfig") {
//            from(files("gradle/gradle-config.versions.toml"))
//        }
}

rootProject.name = "tbmring"
include(":backend")
include(":android")
include(":app")
include(":android:application")
