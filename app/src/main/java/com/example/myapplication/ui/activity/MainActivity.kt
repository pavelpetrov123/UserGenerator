package com.example.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.ui.service.UsersService
import com.example.myapplication.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseFragmentActivity() {

    private val mainActivityModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startUsersService()
    }

    private fun startUsersService() {
        val i = Intent(applicationContext, UsersService::class.java)
        startService(i)
    }

}