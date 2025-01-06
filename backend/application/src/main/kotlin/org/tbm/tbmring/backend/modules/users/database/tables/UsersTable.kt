package org.tbm.tbmring.backend.modules.users.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.tbm.tbmring.backend.modules.ringtones.database.tables.RingtonesTable

object UsersTable : UUIDTable("users__users") {
    val name = varchar("name", 100)
    val email = varchar("email", 100)
    val password = binary("password", 1238991)
    val currentRingtone = optReference("current_ringtone_id", RingtonesTable)
}