package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.store.UserDbStore
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.tools.rx.AppScheduler
import io.reactivex.Flowable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class UsersListFragmentModel(
    private val dbUsersStore: UserDbStore
) : BaseViewModel() {

    val getUsersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers() {
    dbUsersStore.getUsers()
        .map { users -> sortData(users) }
        .subscribeOn(AppScheduler.io())
        .observeOn(AppScheduler.io())
        .subscribe(
         {getUsersLiveData.postValue(it)},
         {Timber.e(it)})
        .addTo(compositeDisposable)
    }

    private fun sortData(list: List<User>) = list
        .sortedBy { user -> user.id }
        .sortedBy { user -> user.name }
        .sortedBy { user -> user.date }
}
