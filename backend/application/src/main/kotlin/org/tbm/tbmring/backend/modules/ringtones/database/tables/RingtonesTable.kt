package org.tbm.tbmring.backend.modules.ringtones.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.tbm.tbmring.backend.modules.ringtones.enums.AlbumType

object RingtonesTable : UUIDTable("album__ringtones") {
    val title = varchar("title", 100)
    val albumType = enumeration("album_type", AlbumType::class)
    val parentId = uuid("parent_id")
    val artistId = uuid("artist_id")
    val albumId = uuid("album_id")
}