package com.example.tddpractise.data.local.repo

import com.example.tddpractise.data.local.dao.UserDao
import javax.inject.Inject

class UserRepo @Inject constructor(val userDao: UserDao) {
}