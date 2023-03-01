package com.example.tddpractise.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.tddpractise.data.local.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val userRepo: UserRepo): ViewModel() {


}