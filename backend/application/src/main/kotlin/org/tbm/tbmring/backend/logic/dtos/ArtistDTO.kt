package org.tbm.tbmring.backend.logic

import kotlinx.serialization.Serializable

@Serializable
data class ArtistDTO(
    val name: String,
    val latestAlbum: AlbumDTO
)