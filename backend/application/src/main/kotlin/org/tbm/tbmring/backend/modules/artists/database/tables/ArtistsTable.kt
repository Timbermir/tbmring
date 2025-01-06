package org.tbm.tbmring.backend.modules.artists.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.tbm.tbmring.backend.modules.albums.database.AlbumsTable

object ArtistsTable : UUIDTable("artists__artists") {
    val name = varchar("name", 255)
    val latestAlbum = reference("latest_album", AlbumsTable)
}