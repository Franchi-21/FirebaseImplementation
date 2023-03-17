package com.firebaseimplementation.adduser.data.database

import com.firebaseimplementation.database.dao.UserDao
import com.firebaseimplementation.database.entities.UserEntity
import javax.inject.Inject

class InsertUserImpl @Inject constructor(
    private val userDao: UserDao,
) : InsertUserHelper {
    override suspend fun insertUser(newUser: UserEntity) {
        userDao.insertUser(newUser = newUser)
    }
}
