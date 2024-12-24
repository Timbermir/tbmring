plugins {
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

group = "org.tbm"
version = "0.0.1"

application {
    mainClass.set("org.tbm.tbmring.backend.ApplicationKt")

    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
    mavenCentral()
}

tasks.register("enableAutoReload") {
    group = "ktor"
    description = "Continuously builds the project while skipping tests and enabling info logs."

    doLast {
        exec {
            commandLine = listOf("../../gradlew", "-t", "build", "-x", "test", "-i")
        }
    }
}

dependencies {
    implementation(platform(libs.exposed.bom))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.request.validation)
    implementation(libs.ktor.server.sessions)
    implementation(libs.ktor.serialization.kotlinx)
    implementation(libs.logback)
    implementation(libs.postgresql)
    implementation(libs.bundles.exposed)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger)
}