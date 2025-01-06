package org.tbm.tbmring.backend.foundation.extensions

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.tbm.tbmring.backend.foundation.ErrorMessage
import org.tbm.tbmring.backend.foundation.Language
import org.tbm.tbmring.backend.foundation.Response
import org.tbm.tbmring.backend.foundation.findEnumByName

suspend inline fun <reified T> RoutingCall.respondSuccess(
    httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
    data: T
) {
    respond(
        httpStatusCode, Response(
            isRequestSuccessful = true,
            httpStatusCode = httpStatusCode.value,
            data = data
        )
    )
}

suspend fun RoutingCall.respondFailure(
    httpStatusCode: HttpStatusCode = HttpStatusCode.BadRequest,
    error: ErrorMessage? = ErrorMessage.General.Unexpected
) {
    respond(
        httpStatusCode, Response<Unit>(
            isRequestSuccessful = false,
            httpStatusCode = httpStatusCode.value,
            error = error?.getErrorMessage(getLanguageHeader())
        )
    )
}

fun RoutingCall.getLanguageHeader() = findEnumByName<Language>(request.headers["language"], Language.ENGLISH)