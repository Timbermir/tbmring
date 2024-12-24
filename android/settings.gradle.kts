pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
        google()
    }
    versionCatalogs {
        create("androidConfiguration") {
            from(files("gradle/android.versions.toml"))
        }
        create("gradleConfiguration") {
            from(files("../gradle/gradle.versions.toml"))
        }
    }
}

rootProject.name = "android"
include(":application")