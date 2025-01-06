package org.tbm.tbmring.backend.modules.users.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.tbm.tbmring.backend.modules.ringtones.database.tables.RingtonesTable

object UserRingtonesTable : UUIDTable("users__ringtones") {
    val userId = reference("user_id", UsersTable)
    val ringtoneId = reference("ringtone_id", RingtonesTable)
}