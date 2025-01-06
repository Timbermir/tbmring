package org.tbm.tbmring.backend

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.plugin.Koin
import org.tbm.tbmring.backend.foundation.extensions.json
import org.tbm.tbmring.backend.modules.authentication.authentication
import org.tbm.tbmring.backend.modules.users.getUsers

fun main(args : Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {

    log.info("sosi")
    environment.config.toMap().forEach {k, v ->
        log.info("$k: $v")
    }

    install(Koin) {
        modules(MainDependencyInjectionModule.create(this@module))
    }

    install(ContentNegotiation) {
        json(this@module.json)
    }

    Database.connect("jdbc:postgresql://localhost:5432/tbmring", user = "timplifier")
    authentication()
    getUsers()
}