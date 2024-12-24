package org.tbm.tbmring.backend.modules.users.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object UsersTable : UUIDTable("users__users") {
    val name = varchar("name", 100)
    val email = varchar("email", 100)
    val currentRingtone = uuid("current_ringtone_id")
}