package org.tbm.tbmring.backend.modules.users.database.tables

import org.jetbrains.exposed.sql.Table
import org.tbm.tbmring.backend.modules.ringtones.database.tables.RingtonesTable

object UserRingtonesTable : Table("user_ringtones") {
    val user = reference("user_id", UsersTable)
    val ringtone = reference("ringtone_id", RingtonesTable)
    override val primaryKey = PrimaryKey(user, ringtone)
}