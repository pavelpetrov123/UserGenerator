package com.example.myapplication.domain.manager.impl

import android.content.Context
import com.example.myapplication.data.store.UserDbStore
import com.example.myapplication.domain.manager.RandomUserGenerator
import com.example.myapplication.domain.manager.UpdateUsersManager
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.tools.Constants.TICK_TIME_MINUTE
import com.example.myapplication.ui.tools.ScheduleTimer
import java.util.concurrent.atomic.AtomicBoolean

class UpdateUsersManagerImpl(
    val context: Context,
    val generator: RandomUserGenerator,
    val userDbStore: UserDbStore
): UpdateUsersManager {

    private val started = AtomicBoolean()

    private val timer: ScheduleTimer = ScheduleTimer(TICK_TIME_MINUTE)

    private val onTimer: () -> Unit = {
        val user: User = generator.generate()
        userDbStore.addUser(user)
    }

    override val isStarted: Boolean
        get() { return started.get() }

    override fun run() {
        timer.listener = onTimer
        timer.start()
        started.set(true)
    }

    override fun stop() {
        timer.stop()
        started.set(false)
    }
}