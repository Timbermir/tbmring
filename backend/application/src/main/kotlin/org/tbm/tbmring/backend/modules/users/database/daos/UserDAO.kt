package org.tbm.tbmring.backend.modules.users.database.daos

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Transaction
import org.tbm.tbmring.backend.foundation.DAO
import org.tbm.tbmring.backend.foundation.toDTO
import org.tbm.tbmring.backend.logic.dtos.UserDTO
import org.tbm.tbmring.backend.modules.ringtones.database.daos.RingtoneDAO
import org.tbm.tbmring.backend.modules.users.database.tables.UserRingtonesTable
import org.tbm.tbmring.backend.modules.users.database.tables.UsersTable
import java.util.*

class UserDAO(id: EntityID<UUID>) : DAO<UserDTO, UsersTable>(id, UsersTable) {

    var name by table.name
    var email by table.email
    var password by table.password
    var currentRingtone by table.currentRingtone
    var allRingtones by RingtoneDAO via UserRingtonesTable

    override val toDTOLambda: Transaction.() -> UserDTO =
        {
            val ringtoneId = currentRingtone
            UserDTO(
                id.value,
                name,
                email,
                if (ringtoneId != null) RingtoneDAO.findById(ringtoneId)?.toDTO() else null,
                allRingtones.toDTO()
            )
        }

    companion object : UUIDEntityClass<UserDAO>(UsersTable)
}