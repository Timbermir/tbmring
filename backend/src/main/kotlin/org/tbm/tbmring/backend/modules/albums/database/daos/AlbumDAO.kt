package org.tbm.tbmring.backend.modules.albums.database.daos

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Transaction
import org.tbm.tbmring.backend.foundation.DAO
import org.tbm.tbmring.backend.modules.albums.database.AlbumsTable
import org.tbm.tbmring.backend.modules.albums.dtos.AlbumDTO
import org.tbm.tbmring.backend.modules.artists.database.daos.ArtistDAO
import java.util.*

class AlbumDAO(id: EntityID<UUID>) : DAO<AlbumDTO, AlbumsTable>(id, AlbumsTable) {
    var name by table.name
    var artistId by table.artistId
    var releaseDate by table.releaseDate

    override val toDTOLambda: Transaction.() -> AlbumDTO
        get() = { AlbumDTO(name, ArtistDAO[artistId].toDTO(), releaseDate) }

    companion object : UUIDEntityClass<AlbumDAO>(AlbumsTable)

}