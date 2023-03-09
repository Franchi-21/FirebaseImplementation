package com.firebaseimplementation.adduser.di

import android.content.Context
import androidx.room.Room
import com.firebaseimplementation.adduser.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, "user_table")
            .build()

    @Singleton
    @Provides
    fun provideUserDao(database: UserDatabase) = database.getUserDao()
}
