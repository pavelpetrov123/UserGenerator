package com.example.myapplication.data.di

import com.example.myapplication.domain.usecase.GetUsersListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetUsersListUseCase> {
        GetUsersListUseCase(get())
    }
}
