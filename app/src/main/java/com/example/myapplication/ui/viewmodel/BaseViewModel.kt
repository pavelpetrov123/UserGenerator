package com.example.myapplication.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.App
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val context: Context?
        get() {
            return App.appInstance
        }

    override fun onCleared() {
        Timber.i("${this::class.java.simpleName}: onCleared")
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}
