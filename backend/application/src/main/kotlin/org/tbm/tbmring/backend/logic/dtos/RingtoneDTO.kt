package org.tbm.tbmring.backend.logic.dtos

import kotlinx.serialization.Serializable
import org.tbm.tbmring.backend.logic.enums.AlbumType

@Serializable
data class RingtoneDTO(
    val title: String,
    val albumType: AlbumType,
    val album: AlbumDTO,
    val artist: ArtistDTO,
)