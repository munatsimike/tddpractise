package com.example.tddpractise.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tddpractise.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(id: Int): User

}