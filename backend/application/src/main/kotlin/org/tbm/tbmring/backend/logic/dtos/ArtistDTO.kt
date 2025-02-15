package org.tbm.tbmring.backend.logic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ArtistDTO(
    val name: String,
    val latestAlbum: AlbumDTO
)