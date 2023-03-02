package com.example.tddpractise.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddpractise.data.local.dao.UserDao
import com.example.tddpractise.model.User
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {
    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context = context, AppDatabase::class.java).build()
        dao = database.userDao
    }

    @Test
    fun readWriteFromDatabase() = runBlocking {
        val user = User(140, "Michael", "Munatsi")
        dao.insertUser(user)
        val result = dao.getUser(140)

        assertThat(result.id).isEqualTo(140)
    }

    @After
    fun closeDB() {
        database.close()
    }

}