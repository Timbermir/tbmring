package org.tbm.tbmring.backend.modules.users

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.getUsers() {
    routing {
        get("/users") {
        }
    }
}