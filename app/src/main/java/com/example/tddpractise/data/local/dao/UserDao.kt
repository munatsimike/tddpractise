package com.example.tddpractise.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tddpractise.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(id: Int): User

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

}