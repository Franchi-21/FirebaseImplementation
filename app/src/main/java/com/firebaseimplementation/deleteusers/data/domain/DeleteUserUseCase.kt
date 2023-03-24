package com.firebaseimplementation.deleteusers.data.domain

import com.firebaseimplementation.deleteusers.data.database.DeleteUserImpl
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val deleteUserImpl: DeleteUserImpl
) {
    suspend fun deleteByName(name: String): Int {
        return deleteUserImpl.deleteByName(name = name)
    }
}
