package com.example.tddpractise.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tddpractise.model.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)
}