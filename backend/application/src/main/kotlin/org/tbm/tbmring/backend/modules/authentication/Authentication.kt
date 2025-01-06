package org.tbm.tbmring.backend.modules.authentication

import at.favre.lib.crypto.bcrypt.BCrypt
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.tbm.tbmring.backend.foundation.ErrorMessage
import org.tbm.tbmring.backend.foundation.Response
import org.tbm.tbmring.backend.foundation.extensions.*
import org.tbm.tbmring.backend.foundation.firstOrNull
import org.tbm.tbmring.backend.logic.dtos.SignInDTO
import org.tbm.tbmring.backend.logic.dtos.SignUpDTO
import org.tbm.tbmring.backend.logic.dtos.SuccessfulAuthenticationDTO
import org.tbm.tbmring.backend.modules.users.database.daos.UserDAO

fun Application.authentication() {

    install(Authentication) {
        jwt("jwt") {
            realm = JWTService.realm
            verifier(JWTService.verifier)
            validate { credential ->
                JWTService.customValidator(credential)
            }

            challenge { defaultScheme, realm ->
                call.respond(
                    HttpStatusCode.Unauthorized,
                    Response<Unit>(
                        isRequestSuccessful = false,
                        httpStatusCode = HttpStatusCode.Unauthorized.value,
                        error = "You are not authorized to perform this action"
                    )
                )
            }
        }
    }

    routing {

        route("authentication") {
            install(RequestValidation) {
                validate<SignUpDTO> { signInDTO ->
                    if (signInDTO.name.length < 5)
                        ValidationResult.Invalid("sosi")
                    else ValidationResult.Valid
                }
            }

            post("sign-in") {
                val signIn = call.receive<SignInDTO>()

                val foundUser = UserDAO.firstOrNull { this.name == signIn.name }
                foundUser?.let {
                    val user = foundUser.toDTO()
                    if (signIn.password.verify(foundUser.password)) {

                        val accessToken = JWTService.createToken(signIn.name, 1)
                        val refreshToken = JWTService.createToken(signIn.name, 7)
                        call.respondSuccess(
                            data = SuccessfulAuthenticationDTO(
                                foundUser.id.value,
                                user.name,
                                user.email,
                                user.activeRingtone,
                                user.allRingtones,
                                accessToken,
                                refreshToken
                            )
                        )
                    }
                } ?: return@post call.respondFailure(error = ErrorMessage.General.NotFound)
            }

            post("sign-up") {
                val signUp = call.receive<SignUpDTO>()

                when {
                    signUp.name in UserDAO.all()
                        .transactionalMap { it.name } -> return@post call.respondFailure(error = AuthenticationErrors.SignUp.NameIsAlreadyInUse)

                    signUp.email in UserDAO.all().transactionalMap { it.email } -> {
                        return@post call.respondFailure(error = AuthenticationErrors.SignUp.EmailIsAlreadyInUse)
                    }
                }

                val accessToken = JWTService.createToken(signUp.name, 1)
                val refreshToken = JWTService.createToken(signUp.name, 7)

                val hashedPassword = signUp.password.toHash()
                val user = UserDAO.newTransactional {
                    name = signUp.name
                    email = signUp.email
                    password = hashedPassword
                }.toDTO()

                log.info(
                    BCrypt.verifyer(
                        BCrypt.Version.VERSION_2A,
                        LongPasswordStrategies.truncate(BCrypt.Version.VERSION_2A)
                    )
                        .verify(signUp.password.toCharArray(), hashedPassword).verified.toString()
                )
                call.respondSuccess(
                    data = SuccessfulAuthenticationDTO(
                        user.id,
                        user.name,
                        user.email,
                        user.activeRingtone,
                        user.allRingtones,
                        accessToken,
                        refreshToken
                    )
                )
            }
        }
    }
}