package com.firebaseimplementation.getusers.data.database

import com.firebaseimplementation.database.User
import kotlinx.coroutines.flow.Flow

interface GetUsersHelper {
    fun getAllUsers(): Flow<List<User>>
}
