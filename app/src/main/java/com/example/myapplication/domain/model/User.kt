package com.example.myapplication.domain.model

import com.example.myapplication.data.db.entity.UserEntity
import java.io.Serializable
import java.util.*

data class User(
    var id: String = "",
    var name: String= "",
    var date: Date
): Serializable {

    fun toUserEntity(): UserEntity {
        return UserEntity(id, name, date)
    }
}