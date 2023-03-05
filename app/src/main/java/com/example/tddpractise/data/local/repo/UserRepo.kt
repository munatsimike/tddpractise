package com.example.tddpractise.data.local.repo

import androidx.lifecycle.LiveData
import com.example.tddpractise.data.local.dao.UserDao
import com.example.tddpractise.model.User
import javax.inject.Inject

class UserRepo @Inject constructor(private val userDao: UserDao) : Repository {
    override suspend fun getUser(id: Int): User = userDao.getUser(id)

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun getAllUsers(): LiveData<List<User>> = userDao.getAllUsers()

}