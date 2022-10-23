package com.example.myapplication.ui.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R

abstract class BaseFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        initViewModel()
    }

    open fun initViewModel() {}

    @LayoutRes
    protected open val layoutResId: Int = R.layout.activity_main

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
