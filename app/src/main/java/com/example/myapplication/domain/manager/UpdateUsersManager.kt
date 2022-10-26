package com.example.myapplication.domain.manager

interface UpdateUsersManager {
    val isStarted: Boolean
    fun run()
    fun stop()
}