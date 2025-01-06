package org.tbm.tbmring.backend.modules.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.tbm.tbmring.backend.foundation.firstOrNull
import org.tbm.tbmring.backend.modules.users.database.daos.UserDAO
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

object JWTService : KoinComponent {
    private val applicationEnvironmentConfig by inject<ApplicationConfig>(named("applicationEnvironmentConfig"))

    private val secret = applicationEnvironmentConfig.property("jwt.privateKey").getString()
    private val issuer = applicationEnvironmentConfig.property("jwt.issuer").getString()
    private val audience = applicationEnvironmentConfig.property("jwt.audience").getString()
    val realm = applicationEnvironmentConfig.property("jwt.realm").getString()

    val verifier: JWTVerifier by lazy {
        JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }

    fun createToken(claim: String, daysLifespan: Int): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("name", claim)
            .withExpiresAt(Date(System.currentTimeMillis() + daysLifespan.days.inWholeMilliseconds))
            .sign(Algorithm.HMAC256(secret))
    }

    fun customValidator(
        credential: JWTCredential,
    ): JWTPrincipal? {
        val name: String? = credential.payload.getClaim("name").asString()
        return UserDAO.firstOrNull { this.name == name }?.let {
            if (credential.payload.audience.contains(audience))
                JWTPrincipal(credential.payload)
            else
                null
        }
    }
}

@OptIn(ExperimentalTime::class)
fun Duration.of(value: Int, durationUnit: DurationUnit) {
    Duration.convert(value.toDouble(), durationUnit, durationUnit)
}