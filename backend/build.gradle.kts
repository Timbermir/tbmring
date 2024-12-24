plugins {
    alias(root.plugins.ktor)
    alias(root.plugins.kotlin.jvm)
    alias(root.plugins.kotlin.serialization)
}

group = "org.tbm"
version = "0.0.1"

application {
    mainClass.set("org.tbm.tbmring.root.ApplicationKt")

    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(root.exposed.bom))
    implementation(root.ktor.server.core)
    implementation(root.ktor.server.netty)
    implementation(root.ktor.server.auth)
    implementation(root.ktor.server.content.negotiation)
    implementation(root.ktor.server.sessions)
    implementation(root.ktor.serialization.kotlinx)
    implementation(root.logback)
    implementation(root.postgresql)
    implementation(root.bundles.exposed)
    implementation(root.koin.ktor)
    implementation(root.koin.logger)
}