package org.tbm.tbmring.backend.foundation

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

abstract class DAO<DTO, TABLE : UUIDTable>(id: EntityID<UUID>, val table: TABLE) : UUIDEntity(id) {


    protected abstract val toDTOLambda: Transaction.() -> DTO

    fun toDTO(): DTO = transaction { toDTOLambda() }
}

fun <DTO, TABLE : UUIDTable> SizedIterable<DAO<DTO, TABLE>>.toDTO() = map { it.toDTO() }