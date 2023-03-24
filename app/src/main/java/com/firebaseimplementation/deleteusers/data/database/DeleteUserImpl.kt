package com.firebaseimplementation.deleteusers.data.database

import com.firebaseimplementation.database.dao.UserDao
import javax.inject.Inject

class DeleteUserImpl @Inject constructor(
    private val userDao: UserDao,
) : DeleteUserHelper {
    override suspend fun deleteByName(name: String): Int {
        return userDao.deleteByName(name = name)
    }
}
