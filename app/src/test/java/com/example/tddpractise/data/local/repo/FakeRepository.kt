package com.example.tddpractise.data.local.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tddpractise.model.User

class FakeRepository : Repository {
    private val users = mutableListOf<User>()

    private val observableUsers = MutableLiveData<List<User>>(users)

    private fun refreshLiveData() {
        observableUsers.postValue(users)
    }

    override suspend fun getUser(id: Int): User {
      return  users.first { it.id == id }
    }

    override suspend fun insertUser(user: User) {
        users.add(user)
        refreshLiveData()
    }

    override fun getAllUsers(): LiveData<List<User>> = observableUsers

}