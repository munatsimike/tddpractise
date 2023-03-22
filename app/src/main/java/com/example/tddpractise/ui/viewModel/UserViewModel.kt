package com.example.tddpractise.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.tddpractise.data.local.repo.UserRepo
import com.example.tddpractise.miscellaneous.state.NetworkState
import com.example.tddpractise.model.User
import com.google.ar.core.dependencies.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepo: UserRepo) : ViewModel() {

    private val _userState = MutableStateFlow<NetworkState>(NetworkState.NotLoading)

    val userStateFlow: StateFlow<NetworkState> = _userState
    suspend fun getUser(id: Int): User = userRepo.getUser(id)


    init {
        getAllUsers()
    }

    suspend fun insertUser(user: User) {
        userRepo.insertUser(user)
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            _userState.value = NetworkState.Loading
            userRepo.getAllUsers().asFlow()
                .catch { error ->
                    _userState.value = error.message?.let { NetworkState.Error(it) }!!
                }
                .map { users ->
                    NetworkState.Success(users)
                }
                .collect { networkState ->
                    _userState.value = networkState
                }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepo.insertUser(user)
        }
    }


}