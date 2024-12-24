package org.tbm.tbmring.backend.foundation

import org.koin.core.module.Module

abstract class KoinModule {
    abstract fun create(): Module
}