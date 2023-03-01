package com.example.tddpractise.di

import android.content.Context
import androidx.room.Room
import com.example.tddpractise.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "appDB.db"
    ).build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao
}