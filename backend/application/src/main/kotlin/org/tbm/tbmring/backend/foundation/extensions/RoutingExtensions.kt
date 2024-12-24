package org.tbm.tbmring.backend.foundation.extensions

import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

inline val Routing.json: Json
    get() {
        val json by inject<Json>()
        return json
    }