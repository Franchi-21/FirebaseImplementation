package com.firebaseimplementation.editusers.data.database

interface EditUsersHelper {
    suspend fun updateUser(name: String, age: Int, oldName: String)

    suspend fun exists(name: String): Int
}
