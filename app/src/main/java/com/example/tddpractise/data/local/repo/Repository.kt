package com.example.tddpractise.data.local.repo

import androidx.lifecycle.LiveData
import com.example.tddpractise.model.User

interface Repository {

    suspend fun getUser(id: Int): User
    suspend fun insertUser(user: User)
    fun getAllUsers(): LiveData<List<User>>

    suspend fun deleteAllUsers()
}