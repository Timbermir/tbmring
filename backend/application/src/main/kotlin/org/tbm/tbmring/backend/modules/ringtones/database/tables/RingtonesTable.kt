package org.tbm.tbmring.backend.modules.ringtones.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.tbm.tbmring.backend.logic.enums.AlbumType
import org.tbm.tbmring.backend.modules.albums.database.AlbumsTable
import org.tbm.tbmring.backend.modules.artists.database.tables.ArtistsTable

object RingtonesTable : UUIDTable("albums__ringtones") {
    val title = varchar("title", 100)
    val albumType = enumeration("album_type", AlbumType::class)
    val artistId = reference("artist_id", ArtistsTable)
    val albumId = reference("album_id", AlbumsTable)
}