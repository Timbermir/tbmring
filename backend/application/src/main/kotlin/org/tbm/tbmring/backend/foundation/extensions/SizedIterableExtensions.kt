package org.tbm.tbmring.backend.foundation.extensions

import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

fun <T, R> SizedIterable<T>.transactionalMap(transform: (T) -> R) = transaction { map(transform) }
