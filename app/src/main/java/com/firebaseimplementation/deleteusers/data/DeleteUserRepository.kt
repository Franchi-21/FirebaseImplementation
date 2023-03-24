package com.firebaseimplementation.deleteusers.data

import com.firebaseimplementation.deleteusers.data.domain.DeleteUserUseCase
import javax.inject.Inject

class DeleteUserRepository @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase
) {
    suspend fun deleteByName(name: String): Int {
        return deleteUserUseCase.deleteByName(name = name)
    }
}