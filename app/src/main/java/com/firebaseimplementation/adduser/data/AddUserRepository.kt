package com.firebaseimplementation.adduser.data

import com.firebaseimplementation.adduser.data.domain.GetUserUseCase
import javax.inject.Inject

class AddUserRepository @Inject constructor(private val userUseCase: GetUserUseCase) {
    suspend fun insertUser(name: String, age: Int) {
        userUseCase.insertUser(name = name, age = age)
    }
}
