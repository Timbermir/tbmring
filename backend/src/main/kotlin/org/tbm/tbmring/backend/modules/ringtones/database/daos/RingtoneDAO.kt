package org.tbm.tbmring.backend.modules.ringtones.database.daos

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Transaction
import org.tbm.tbmring.backend.foundation.DAO
import org.tbm.tbmring.backend.modules.albums.database.daos.AlbumDAO
import org.tbm.tbmring.backend.modules.artists.database.daos.ArtistDAO
import org.tbm.tbmring.backend.modules.ringtones.database.tables.RingtonesTable
import org.tbm.tbmring.backend.modules.ringtones.dtos.RingtoneDTO
import java.util.*

class RingtoneDAO(id: EntityID<UUID>) : DAO<RingtoneDTO, RingtonesTable>(id, RingtonesTable) {

    var title by table.title
    var albumType by table.albumType
    var parentId by table.parentId
    var artistId by table.artistId
    var albumId by table.albumId

    companion object : UUIDEntityClass<RingtoneDAO>(RingtonesTable)

    override val toDTOLambda: Transaction.() -> RingtoneDTO =
        {
            RingtoneDTO(
                title,
                albumType,
                AlbumDAO[albumId].toDTO(),
                ArtistDAO[artistId].toDTO()
            )
        }
}