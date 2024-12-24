package org.tbm.tbmring.backend.modules.albums.dtos

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.tbm.tbmring.backend.modules.artists.dtos.ArtistDTO

@Serializable
data class AlbumDTO(
    val name: String,
    val artist: ArtistDTO,
    val releaseDate: LocalDate
)