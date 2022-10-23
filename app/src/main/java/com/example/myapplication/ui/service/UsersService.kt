@file:Suppress("DEPRECATION", "OverrideDeprecatedMigration", "OverrideDeprecatedMigration")

package com.example.myapplication.ui.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.myapplication.ui.tools.APP_NOTIFICATION_ID
import com.example.myapplication.ui.tools.NotificationSender

import com.example.myapplication.R
import com.example.myapplication.domain.manager.UpdateUsersManager

import org.koin.android.ext.android.inject

@Suppress("OverrideDeprecatedMigration", "OverrideDeprecatedMigration")
class UsersService: Service() {

    private val usersManager: UpdateUsersManager by inject()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val result = super.onStartCommand(intent, flags, startId)
        runForeground()
        usersManager.run()
        return result
    }

    private fun runForeground() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationSender(applicationContext, manager)
            .getAppNotification(
                getString(R.string.users_service_ready),
                getString(R.string.users_service_running),
                applicationContext, true)
        startForeground(APP_NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForegroundService()
    }

    override fun onBind(intent: Intent?): IBinder? { return null }

    private fun stopForegroundService() {
        stopForeground(true)
    }
}