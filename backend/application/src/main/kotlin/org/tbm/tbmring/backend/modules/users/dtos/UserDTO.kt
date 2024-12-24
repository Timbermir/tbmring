package org.tbm.tbmring.backend.modules.users.dtos

import kotlinx.serialization.Serializable
import org.tbm.tbmring.backend.modules.ringtones.dtos.RingtoneDTO

@Serializable
data class UserDTO(
    val name: String,
    val email: String,
    val activeRingtone: RingtoneDTO,
    val allRingtones: List<RingtoneDTO>
)