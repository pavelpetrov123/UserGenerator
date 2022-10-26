package com.example.myapplication.ui.viewmodel

import android.content.Intent
import com.example.myapplication.domain.manager.UpdateUsersManager
import com.example.myapplication.ui.service.UsersService

class MainActivityViewModel(
    private val usersManager: UpdateUsersManager) : BaseViewModel() {

    fun startServiceIfNeed() {
        if (!usersManager.isStarted) {
            startUsersService()
        }
    }

    private fun startUsersService() {
        val i = Intent(context, UsersService::class.java)
        context?.startService(i)
    }
}
