package com.firebaseimplementation.editusers.data.database

import com.firebaseimplementation.database.dao.UserDao
import javax.inject.Inject

class EditUsersImpl @Inject constructor(
    private val userDao: UserDao,
) : EditUsersHelper {
    override suspend fun updateUser(
        name: String,
        age: Int,
        oldName: String,
    ) {
        userDao.updateUser(
            name = name,
            age = age,
            oldName = oldName,
        )
    }

    override suspend fun exists(name: String): Int {
        return userDao.exists(name = name)
    }
}
