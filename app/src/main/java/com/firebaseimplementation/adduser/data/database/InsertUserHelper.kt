package com.firebaseimplementation.adduser.data.database

import com.firebaseimplementation.database.entities.UserEntity

interface InsertUserHelper {
    suspend fun insertUser(newUser: UserEntity)
}