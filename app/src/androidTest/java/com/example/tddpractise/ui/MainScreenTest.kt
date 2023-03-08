package com.example.tddpractise.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddpractise.DisplayUsersScreen
import com.example.tddpractise.ToggleAddUserAndDisplayUsersScreen
import com.example.tddpractise.model.User
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    var composeRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun show_addUser_screen_and_hide_display_users_screen_when_show_user_screen_is_true() {
        composeRule.setContent {
            ToggleAddUserAndDisplayUsersScreen(showUserScreen = true)
        }
        assertNodeWithTextIsDisplayed("Name")
        assertNodeWithTextIsDisplayed("Surname")
        assertNodeWithTextIsDisplayed("SUBMIT")
        composeRule.onNodeWithTag("users list").assertDoesNotExist()
    }
    
    @Test
    fun show_display_users_screen_and_hide_addUser_screen_when_show_user_screen_is_false() {
        composeRule.setContent {
            ToggleAddUserAndDisplayUsersScreen(showUserScreen = false)
        }
        assertNodeWithTextDoesNotExist("Name")
        assertNodeWithTextDoesNotExist("Surname")
        assertNodeWithTextDoesNotExist("SUBMIT")
        composeRule.onNodeWithTag("users list").assertExists()
    }

    @Test
    fun should_display_a_list_of_users() {
        composeRule.setContent {
            DisplayUsersScreen(
                users = listOf(
                    User(1, "Michael", "Munatsi"), User(2, "Tarry", "Bingura")
                )
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

    private fun assertNodeWithTextDoesNotExist(text: String) {
        composeRule.onNodeWithText(text).assertDoesNotExist()
    }
}