package org.tbm.tbmring.backend.modules.artists.database.daos

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Transaction
import org.tbm.tbmring.backend.foundation.DAO
import org.tbm.tbmring.backend.modules.albums.database.daos.AlbumDAO
import org.tbm.tbmring.backend.modules.artists.database.tables.ArtistsTable
import org.tbm.tbmring.backend.logic.dtos.ArtistDTO
import java.util.*

class ArtistDAO(id: EntityID<UUID>) : DAO<ArtistDTO, ArtistsTable>(id, ArtistsTable) {
    companion object : UUIDEntityClass<ArtistDAO>(ArtistsTable)

    var name by table.name
    var latestAlbum by table.latestAlbum

    override val toDTOLambda: Transaction.() -> ArtistDTO
        get() = { ArtistDTO(name, AlbumDAO[latestAlbum].toDTO()) }
}