package com.example.myapplication.data.store

import com.example.myapplication.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Single

interface UserDbStore {
    fun addUser(user: User): Single<User>
    fun getUsers(): Flowable<List<User>>
}