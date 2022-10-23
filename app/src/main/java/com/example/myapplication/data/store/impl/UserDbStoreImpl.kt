package com.example.myapplication.data.store.impl

import com.example.myapplication.data.db.dao.UserDao
import com.example.myapplication.data.store.UserDbStore
import com.example.myapplication.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Single

class UserDbStoreImpl(private val usersDao: UserDao): UserDbStore {
    override fun addUser(user: User): Single<User> {
        usersDao.insert(user.toUserEntity())
        return Single.fromCallable { user }
    }

    override fun getUsers(): Flowable<List<User>> {
        return usersDao.getAll().map { list -> list.map { it.toUser() } }
    }
}