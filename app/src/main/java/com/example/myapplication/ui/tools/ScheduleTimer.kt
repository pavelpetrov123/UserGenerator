package com.example.myapplication.ui.tools

import java.util.*

class ScheduleTimer(private val timerPeriod: Long) {

    private var timer: Timer? = null

    var listener: (() -> Unit)? = null

    private fun schedule() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                 listener?.invoke()
            }
        }, 0, timerPeriod)
    }

    fun start() {
        stop()
        schedule()
    }

    fun stop() {
        timer?.purge()
        timer?.cancel()
        timer = null
    }
}