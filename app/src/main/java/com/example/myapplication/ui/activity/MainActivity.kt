package com.example.myapplication.ui.activity

import android.os.Bundle
import com.example.myapplication.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseFragmentActivity() {

    private val mainActivityModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityModel.startServiceIfNeed()
    }
}