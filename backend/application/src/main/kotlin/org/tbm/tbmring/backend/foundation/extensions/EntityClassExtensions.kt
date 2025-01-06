package org.tbm.tbmring.backend.foundation.extensions

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

inline fun <reified T : Entity<UUID>> EntityClass<UUID, T>.newTransactional(noinline block: T.() -> Unit) =
    transaction { new(block) }