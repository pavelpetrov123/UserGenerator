package com.example.myapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.User
import java.util.*

@Entity(tableName = "users_data")
data class UserEntity(
    @PrimaryKey
    var id: String = "",
    var name: String= "",
    var date: Date
) {

    fun toUser(): User {
        return User(id, name, date)
    }
}