import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(gradleConfiguration.versions.jdk.get().toInt()))
        vendor.set(JvmVendorSpec.AMAZON)
        implementation.set(JvmImplementation.VENDOR_SPECIFIC)
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.values().first { it.target.contains(gradleConfiguration.versions.jdk.get()) })
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(gradleConfiguration.versions.jdk.get().toInt()))
        vendor.set(JvmVendorSpec.AMAZON)
        implementation.set(JvmImplementation.VENDOR_SPECIFIC)
    }
}