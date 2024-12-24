package org.tbm.tbmring.backend.modules.albums.database

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object AlbumsTable : UUIDTable("artists__albums") {
    val name = varchar("name", 255)
    val artistId = uuid("artist_id")
    val releaseDate = date("release_date")
}