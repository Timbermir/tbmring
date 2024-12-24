package org.tbm.tbmring.backend.modules.artists.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object ArtistsTable : UUIDTable("artists__artists") {
    val name = varchar("name", 255)
    val latestAlbum = uuid("latest_album")
}