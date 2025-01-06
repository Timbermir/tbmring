package org.tbm.tbmring.backend.modules.authentication

import org.tbm.tbmring.backend.foundation.ErrorMessage

object AuthenticationErrors {

    object SignUp {
        object NameIsAlreadyInUse : ErrorMessage("Name is already in use", "Имя уже занято")
        object EmailIsAlreadyInUse : ErrorMessage("Email is already in use", "Email уже занят")
    }
}