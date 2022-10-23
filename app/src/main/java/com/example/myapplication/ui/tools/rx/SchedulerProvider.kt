package com.example.myapplication.ui.tools.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
    fun newThread(): Scheduler
}