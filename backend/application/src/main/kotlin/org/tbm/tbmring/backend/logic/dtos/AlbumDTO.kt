package org.tbm.tbmring.backend.logic.dtos

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDTO(
    val name: String,
    val artist: ArtistDTO,
    val releaseDate: LocalDate
)