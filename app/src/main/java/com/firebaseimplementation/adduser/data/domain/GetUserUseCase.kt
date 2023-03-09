package com.firebaseimplementation.adduser.data.domain

import com.firebaseimplementation.adduser.data.database.dao.UserDao
import com.firebaseimplementation.adduser.data.database.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(name: String, age: Int) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(UserEntity(name = name, age = age))
        }
    }
}
