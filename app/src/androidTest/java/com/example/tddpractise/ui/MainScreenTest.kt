package com.example.tddpractise.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddpractise.data.local.dao.UserDao
import com.example.tddpractise.data.local.repo.UserRepo
import com.example.tddpractise.getOrAwaitValue
import com.example.tddpractise.miscellaneous.state.NetworkState
import com.example.tddpractise.model.User
import com.example.tddpractise.ui.screens.DisplayAddUserScreen
import com.example.tddpractise.ui.screens.DisplayUsersScreen
import com.example.tddpractise.ui.viewModel.UserViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import io.mockk.*

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    var composeRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun should_display_add_user_screen() {
       val userViewModel = mockk<UserViewModel>()
        composeRule.setContent {
            DisplayAddUserScreen(userViewModel)
        }
        assertNodeWithTextIsDisplayed("Name")
        assertNodeWithTextIsDisplayed("Surname")
        assertNodeWithTextIsDisplayed("SUBMIT")
    }

    @Test
    fun should_display_a_list_of_users() {
        composeRule.setContent {
            val users = listOf(
                User(1, "Michael", "Munatsi"), User(2, "Tarry", "Bingura")
            )

            val usersState = remember {
                mutableStateOf(NetworkState.Success(users))
            }

            DisplayUsersScreen(
                users = usersState
            )
        }
        assertNodeWithTextIsDisplayed("Michael")
        assertNodeWithTextIsDisplayed("Munatsi")
        assertNodeWithTextIsDisplayed("Tarry")
        assertNodeWithTextIsDisplayed("Bingura")
    }

    private fun assertNodeWithTextIsDisplayed(text: String) {
        composeRule.onNodeWithText(text).assertIsDisplayed()
    }
}