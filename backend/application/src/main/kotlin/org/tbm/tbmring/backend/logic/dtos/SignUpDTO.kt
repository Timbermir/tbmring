package org.tbm.tbmring.backend.logic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDTO(
    val name: String,
    val email: String,
    val password: String
)