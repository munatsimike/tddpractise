package com.example.tddpractise.data.local

import androidx.room.RoomDatabase
import com.example.tddpractise.data.local.dao.UserDao

abstract class AppDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}