package com.example.tddpractise.miscellaneous.state

import com.example.tddpractise.model.User

sealed class NetworkState {
    object Loading: NetworkState()
    object NotLoading: NetworkState()
    class Success(val users: List<User>): NetworkState()
    class Error(message: String): NetworkState()
}