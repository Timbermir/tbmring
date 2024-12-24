package org.tbm.tbmring.backend.modules.users.database.daos

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Transaction
import org.tbm.tbmring.backend.foundation.DAO
import org.tbm.tbmring.backend.foundation.toDTO
import org.tbm.tbmring.backend.modules.ringtones.database.daos.RingtoneDAO
import org.tbm.tbmring.backend.modules.users.database.tables.UserRingtonesTable
import org.tbm.tbmring.backend.modules.users.database.tables.UsersTable
import org.tbm.tbmring.backend.modules.users.dtos.UserDTO
import java.util.*

class UserDAO(id: EntityID<UUID>) : DAO<UserDTO, UsersTable>(id, UsersTable) {

    var name by table.name
    var email by table.email
    var currentRingtone by table.currentRingtone
    var allRingtones by RingtoneDAO via UserRingtonesTable

    override val toDTOLambda: Transaction.() -> UserDTO =
        { UserDTO(name, email, RingtoneDAO[currentRingtone].toDTO(), allRingtones.toDTO()) }

    companion object : UUIDEntityClass<UserDAO>(UsersTable)
}