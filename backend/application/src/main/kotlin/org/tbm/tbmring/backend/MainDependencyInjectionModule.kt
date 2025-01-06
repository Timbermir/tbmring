package org.tbm.tbmring.backend

import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.tbm.tbmring.backend.foundation.KoinModule
import org.tbm.tbmring.backend.logic.serializers.UUIDSerializer
import java.util.*

object MainDependencyInjectionModule : KoinModule() {

    override fun create(application: Application): Module {
        return module {
            singleOf(::generateJson)
            single(named("applicationEnvironmentConfig")) { createApplicationEnvironmentConfig(application) }
        }
    }

    private fun createApplicationEnvironmentConfig(application: Application): ApplicationConfig {
        return application.environment.config
    }

    private fun generateJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = true
            encodeDefaults = true
            serializersModule = SerializersModule {
                contextual(UUID::class, UUIDSerializer)
            }
        }
    }
}