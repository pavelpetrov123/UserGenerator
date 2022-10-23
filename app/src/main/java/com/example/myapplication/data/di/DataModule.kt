package com.example.myapplication.data.di

import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.data.db.dao.UserDao
import com.example.myapplication.domain.manager.impl.RandomUserGeneratorImpl
import com.example.myapplication.data.store.UserDbStore
import com.example.myapplication.domain.manager.RandomUserGenerator
import com.example.myapplication.domain.manager.UpdateUsersManager
import com.example.myapplication.data.store.impl.UserDbStoreImpl
import com.example.myapplication.domain.manager.impl.UpdateUsersManagerImpl
import org.koin.dsl.module

val storeModule = module {
    single<UserDbStore> {
        UserDbStoreImpl(get())
    }
    factory<UserDao> {
        AppDatabase.getInstance(get()).userDao()
    }
    single<RandomUserGenerator> {
        RandomUserGeneratorImpl()
    }
    single<UpdateUsersManager> {
        UpdateUsersManagerImpl(get(), get(), get())
    }

}
