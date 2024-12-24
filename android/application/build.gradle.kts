plugins {
    alias(libs.plugins.agp.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    val androidConfig = androidConfiguration.versions
    namespace = androidConfig.common.namespace.get()
    compileSdk = androidConfig.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = androidConfig.common.namespace.get().substringBefore(".android")
        minSdk = androidConfig.sdk.min.get().toInt()
        targetSdk = androidConfig.sdk.compile.get().toInt()
        versionCode = androidConfig.versioning.code.get().toInt()
        versionName =
            "${androidConfig.versioning.major.get()}.${androidConfig.versioning.feature.get()}.${androidConfig.versioning.patch.get()}"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDefault = true
        }
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.values()[gradleConfiguration.versions.jdk.get().toInt() - 1]
        targetCompatibility =
            JavaVersion.values()[gradleConfiguration.versions.jdk.get().toInt() - 1]
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.coil.bom))
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.kotlinx.android)
    implementation(libs.bundles.koin.androidx)
    implementation(libs.bundles.orbit.mvi)
    implementation(libs.bundles.androidx.compose)
    ksp(libs.compose.destinations.ksp)
}