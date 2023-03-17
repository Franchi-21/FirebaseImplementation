package com.firebaseimplementation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firebaseimplementation.database.dao.UserDao
import com.firebaseimplementation.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
