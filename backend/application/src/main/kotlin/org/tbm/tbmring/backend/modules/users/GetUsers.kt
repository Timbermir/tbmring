package org.tbm.tbmring.backend.modules.users

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.tbm.tbmring.backend.modules.users.database.daos.UserDAO

fun Application.getUsers() {
    routing {
        get("/users") {
            call.respondText("gay")
        }
    }
}