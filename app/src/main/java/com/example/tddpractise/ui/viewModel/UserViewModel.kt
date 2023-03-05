package com.example.tddpractise.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tddpractise.data.local.repo.Repository
import com.example.tddpractise.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepo: Repository) : ViewModel() {
    suspend fun getUser(id: Int): User = userRepo.getUser(id)

    suspend fun insertUser(user: User) {
        userRepo.insertUser(user)

    }

    fun getAllUsers(): LiveData<List<User>> = userRepo.getAllUsers()


}