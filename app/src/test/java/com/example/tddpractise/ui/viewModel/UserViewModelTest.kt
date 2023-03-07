package com.example.tddpractise.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tddpractise.data.local.repo.FakeRepository
import com.example.tddpractise.data.local.repo.Repository
import com.example.tddpractise.getOrAwaitValue
import com.example.tddpractise.model.User
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)

@RunWith(JUnit4::class)
class UserViewModelTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userViewModel: UserViewModel
    private lateinit var fakeRepository: Repository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        userViewModel = UserViewModel(fakeRepository)
        populateUsersList()
    }

    @Test
    fun getUserFromDatabase() = runTest {
        val user = userViewModel.getUser(2)
        assertThat(user).isEqualTo(User(2, "Tarry", "Bingura"))
    }

    @Test
    fun addUserToDatabase() = runTest{
        val user = User(4, "Jon", "Bond")
        userViewModel.insertUser(user)
        val result = userViewModel.getUser(4)
        assertThat(result).isEqualTo(user)
    }

    @Test
    fun fetchAllUsersFromDB()= runTest {
        val result = userViewModel.getAllUsers().getOrAwaitValue()
        assertThat(result.size).isEqualTo(3)
    }


    private fun populateUsersList() = runTest {

        val users = listOf(
            User(1, "Michael", "Munatsi"),
            User(2, "Tarry", "Bingura"),
            User(3, "vimbai", "chirimanyemba")
        )

        users.forEach { user: User -> fakeRepository.insertUser(user) }
    }
}