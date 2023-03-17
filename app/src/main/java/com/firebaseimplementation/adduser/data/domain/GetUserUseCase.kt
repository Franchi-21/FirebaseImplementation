package com.firebaseimplementation.adduser.data.domain

import com.firebaseimplementation.adduser.data.database.InsertUserImpl
import com.firebaseimplementation.database.entities.UserEntity
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val insertImpl: InsertUserImpl) {
    suspend fun insertUser(name: String, age: Int) {
        insertImpl.insertUser(UserEntity(name = name, age = age))
    }
}
