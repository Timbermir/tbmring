package org.tbm.tbmring.backend.foundation

abstract class ErrorMessage(open val englishErrorMessage: String, open val russianErrorMessage: String) {

    object General {
        object Unexpected : ErrorMessage("Unexpected error", "Неожиданная ошибка")
        object NotFound : ErrorMessage("Resource not found", "Ресурс не найден")
    }

    fun getErrorMessage(language: Language) = when (language) {
        Language.ENGLISH -> englishErrorMessage
        Language.RUSSIAN -> russianErrorMessage
    }
}