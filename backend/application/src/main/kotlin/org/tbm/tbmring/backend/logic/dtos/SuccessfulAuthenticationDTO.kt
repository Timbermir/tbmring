package org.tbm.tbmring.backend.logic.dtos

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SuccessfulAuthenticationDTO(
    @Contextual
    val id: UUID,
    val name: String,
    val email: String,
    val activeRingtone: RingtoneDTO? = null,
    val allRingtones: List<RingtoneDTO> = emptyList(),
    val accessToken: String,
    val refreshToken: String
)