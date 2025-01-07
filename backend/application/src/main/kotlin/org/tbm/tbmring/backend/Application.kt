package org.tbm.tbmring.backend

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.plugin.Koin
import org.tbm.tbmring.backend.foundation.extensions.getStringProperty
import org.tbm.tbmring.backend.foundation.extensions.json
import org.tbm.tbmring.backend.modules.albums.database.AlbumsTable
import org.tbm.tbmring.backend.modules.artists.database.tables.ArtistsTable
import org.tbm.tbmring.backend.modules.authentication.authentication
import org.tbm.tbmring.backend.modules.ringtones.database.tables.RingtonesTable
import org.tbm.tbmring.backend.modules.users.database.tables.UserRingtonesTable
import org.tbm.tbmring.backend.modules.users.database.tables.UsersTable
import org.tbm.tbmring.backend.modules.users.getUsers

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {

    install(Koin) {
        modules(MainDependencyInjectionModule.create(this@module))
    }

    install(ContentNegotiation) {
        json(this@module.json)
    }

    Database.connect(
        "jdbc:postgresql://localhost:5432/tbmring",
        user = environment.config.getStringProperty("database.user"),
        password = environment.config.getStringProperty("database.password")
    )

    transaction {
        SchemaUtils.create(UsersTable, RingtonesTable, ArtistsTable, AlbumsTable, UserRingtonesTable)
    }
    authentication()
    getUsers()
}