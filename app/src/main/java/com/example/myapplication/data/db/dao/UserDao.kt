package com.example.myapplication.data.db.dao

import androidx.room.*
import com.example.myapplication.data.db.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM users_data")
    fun getAll(): Flowable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: UserEntity?)

    @Query("DELETE FROM users_data")
    fun clearTable()
}