package org.tbm.tbmring.backend.foundation

import io.ktor.server.application.*
import org.koin.core.module.Module

abstract class KoinModule {
    abstract fun create(application: Application): Module
}