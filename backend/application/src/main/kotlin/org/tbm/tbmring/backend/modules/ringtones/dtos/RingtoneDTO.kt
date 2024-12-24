package org.tbm.tbmring.backend.modules.ringtones.dtos

import kotlinx.serialization.Serializable
import org.tbm.tbmring.backend.modules.albums.dtos.AlbumDTO
import org.tbm.tbmring.backend.modules.artists.dtos.ArtistDTO
import org.tbm.tbmring.backend.modules.ringtones.enums.AlbumType

@Serializable
data class RingtoneDTO(
    val title: String,
    val albumType : AlbumType,
    val album : AlbumDTO,
    val artist: ArtistDTO,
)