package org.tbm.tbmring.backend.modules.albums.database

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.tbm.tbmring.backend.modules.artists.database.tables.ArtistsTable

object AlbumsTable : UUIDTable("artists__albums") {
    val name = varchar("name", 255)
    val artistId = reference("artist_id", ArtistsTable)
    val releaseDate = date("release_date")
}