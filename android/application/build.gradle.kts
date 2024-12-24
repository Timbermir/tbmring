plugins {
    alias(root.plugins.agp.application)
    alias(root.plugins.kotlin.android)
}

android {
    namespace = "org.tbm.tbmring.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.tbm.tbmring.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(root.appcompat.v7)
    testImplementation(root.junit)
    androidTestImplementation(root.runner)
    androidTestImplementation(root.espresso.core)
}