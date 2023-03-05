package com.example.tddpractise.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddpractise.data.local.dao.UserDao
import com.example.tddpractise.getOrAwaitValue
import com.example.tddpractise.model.User
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context = context, AppDatabase::class.java).build()
        dao = database.userDao
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun readWriteFromDatabase() =  runTest {
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