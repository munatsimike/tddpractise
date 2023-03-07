package com.example.tddpractise.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tddpractise.data.local.dao.UserDao
import com.example.tddpractise.getOrAwaitValue
import com.example.tddpractise.model.User
import com.google.ar.core.Config
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest

class AppDatabaseTest {
    @get:Rule
    var hilt = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setUp() {
        hilt.inject()
        dao = database.userDao
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun readWriteFromDatabase() = runTest {
        val user = User(140, "Michael", "Munatsi")
        dao.insertUser(user)
        val result = dao.getUser(140)

        assertThat(result.id).isEqualTo(140)
    }

    @Test
    fun getAllUsersFromDatabase() = runTest {
        val users = listOf(User(1, "me", "mo"), User(2, "mike", "munatsi"))
        users.forEach { user: User -> dao.insertUser(user) }
        val result = dao.getAllUsers().getOrAwaitValue()

        assertThat(result.size).isEqualTo(2)
    }
}