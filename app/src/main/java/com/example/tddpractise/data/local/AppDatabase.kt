package com.example.tddpractise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tddpractise.data.local.dao.UserDao
import com.example.tddpractise.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}