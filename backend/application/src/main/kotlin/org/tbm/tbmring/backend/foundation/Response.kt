package org.tbm.tbmring.backend.foundation

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val isRequestSuccessful: Boolean,
    val httpStatusCode: Int,
    val error: String? = null,
    val data: T? = null
)