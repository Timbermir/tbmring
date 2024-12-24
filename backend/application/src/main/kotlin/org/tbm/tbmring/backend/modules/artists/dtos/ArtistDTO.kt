package org.tbm.tbmring.backend.modules.artists.dtos

import kotlinx.serialization.Serializable
import org.tbm.tbmring.backend.modules.albums.dtos.AlbumDTO

@Serializable
data class ArtistDTO(
    val name: String,
    val latestAlbum: AlbumDTO
)