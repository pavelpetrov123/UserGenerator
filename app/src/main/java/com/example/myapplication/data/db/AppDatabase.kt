package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.db.dao.UserDao
import com.example.myapplication.data.db.entity.UserEntity

@TypeConverters(DataTypeConverter::class)
@Database(
        entities = [
            UserEntity::class,
        ],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "AppDatabase"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    //.fallbackToDestructiveMigrationFrom(1)
                    .build()
    }

    abstract fun userDao(): UserDao
}