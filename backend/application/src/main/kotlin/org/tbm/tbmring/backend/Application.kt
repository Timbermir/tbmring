package org.tbm.tbmring.backend

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.plugin.Koin
import org.tbm.tbmring.backend.foundation.extensions.json
import org.tbm.tbmring.backend.modules.users.getUsers

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {

    install(Koin) {
        modules(MainDependencyInjectionModule.create())
    }

    install(ContentNegotiation) {
        json(this@module.json)
    }

    Database.connect("jdbc:postgresql://localhost:5432/tbmring", user = "timplifier")
    getUsers()
}