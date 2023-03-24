package com.firebaseimplementation.deleteusers.data.database

interface DeleteUserHelper {
    suspend fun deleteByName(name: String): Int
}
