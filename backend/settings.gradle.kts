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

rootProject.name = "backend"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
        google()
    }
    versionCatalogs {

        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
//        create("androidConfig") {
//            from(files("gradle/android-config.versions.toml"))
//        }
//
//        create("gradleConfig") {
//            from(files("gradle/gradle-config.versions.toml"))
//        }
}