package org.tbm.tbmring.backend

import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.tbm.tbmring.backend.foundation.KoinModule

object MainDependencyInjectionModule : KoinModule() {

    override fun create(): Module {
        return module {
            singleOf(::generateJson)
        }
    }

    private fun generateJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
            encodeDefaults = true
        }
    }
}