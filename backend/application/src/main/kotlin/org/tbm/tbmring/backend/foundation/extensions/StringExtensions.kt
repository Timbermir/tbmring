package org.tbm.tbmring.backend.foundation.extensions

import at.favre.lib.crypto.bcrypt.BCrypt
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies

fun String.toHash() = BCrypt.with(LongPasswordStrategies.truncate(BCrypt.Version.VERSION_2A))
    .hash(6, this.toCharArray())

fun String.verify(hash: ByteArray) = BCrypt.verifyer(
    BCrypt.Version.VERSION_2A,
    LongPasswordStrategies.truncate(BCrypt.Version.VERSION_2A)
)
    .verify(this.toCharArray(), hash).verified