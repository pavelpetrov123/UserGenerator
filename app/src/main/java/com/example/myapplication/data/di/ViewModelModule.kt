package com.example.myapplication.data.di

import com.example.myapplication.ui.viewmodel.MainActivityViewModel
import com.example.myapplication.ui.viewmodel.UserDetailsFragmentModel
import com.example.myapplication.ui.viewmodel.UsersListFragmentModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { UsersListFragmentModel(get()) }
    viewModel { UserDetailsFragmentModel() }
}