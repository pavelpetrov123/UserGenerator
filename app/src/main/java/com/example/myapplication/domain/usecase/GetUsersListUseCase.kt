package com.example.myapplication.domain.usecase

import com.example.myapplication.data.store.UserDbStore
import com.example.myapplication.domain.model.User
import io.reactivex.Flowable

class GetUsersListUseCase(
    private val dbUsersStore: UserDbStore
) {
    fun getUsersList(sorted: Boolean = true): Flowable<List<User>> {
        return dbUsersStore.getUsers()
        .map { users ->
            if (sorted) {
                sortData(users)
            } else {
                users
            }
        }
    }

    private fun sortData(list: List<User>) = list
        .sortedBy { user -> user.id }
        .sortedBy { user -> user.name }
        .sortedBy { user -> user.date }
}