package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.GetUsersListUseCase
import com.example.myapplication.ui.tools.rx.AppScheduler
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class UsersListFragmentModel(
    private val getUsersUseCase: GetUsersListUseCase
) : BaseViewModel() {

    val getUsersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    val lastUsersList: List<User>?
        get() {
            return getUsersLiveData.value
        }

    fun getUsers() {
        getUsersUseCase.getUsersList()
        .subscribeOn(AppScheduler.io())
        .observeOn(AppScheduler.io())
        .subscribe(
            { getUsersLiveData.postValue(it) },
            { Timber.e(it) })
        .addTo(compositeDisposable)
    }
}
