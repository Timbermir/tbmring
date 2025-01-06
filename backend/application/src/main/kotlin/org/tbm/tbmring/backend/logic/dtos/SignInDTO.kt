package org.tbm.tbmring.backend.logic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SignInDTO(
    val name: String,
    val password: String
)