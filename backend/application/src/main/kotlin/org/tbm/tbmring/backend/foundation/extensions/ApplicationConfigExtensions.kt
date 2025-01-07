package org.tbm.tbmring.backend.foundation.extensions

import io.ktor.server.config.*

fun ApplicationConfig.getStringProperty(path: String) = property(path).getString()

fun ApplicationConfig.getNullableStringProperty(path: String) = propertyOrNull(path)?.getString()