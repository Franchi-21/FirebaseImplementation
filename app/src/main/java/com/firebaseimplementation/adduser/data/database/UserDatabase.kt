package com.firebaseimplementation.adduser.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firebaseimplementation.adduser.data.database.dao.UserDao
import com.firebaseimplementation.adduser.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
