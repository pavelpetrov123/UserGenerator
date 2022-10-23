package com.example.myapplication.ui

import android.app.Application
import com.example.myapplication.data.di.storeModule
import com.example.myapplication.data.di.viewModelModule
import timber.log.Timber

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class App: Application() {

    companion object {
        @JvmStatic var appInstance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        initLogging()
        initKoin()
    }

    private fun initLogging() {
         Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(storeModule, viewModelModule)
        }
    }
}