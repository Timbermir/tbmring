package org.tbm.tbmring.backend.logic

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDTO(
    val name: String,
    val artist: ArtistDTO,
    val releaseDate: LocalDate
)