package org.tbm.tbmring.backend.foundation.extensions

import io.ktor.server.application.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

inline val Application.json: Json
    get() {
        val json by inject<Json>()
        return json
    }