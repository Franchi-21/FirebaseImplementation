package com.firebaseimplementation.getusers.data.domain

import com.firebaseimplementation.getusers.data.database.GetUsersImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val getUsersImpl: GetUsersImpl) {
    suspend fun getAllUsers() {
        coroutineScope {
            getUsersImpl
                .getAllUsers()
                .flowOn(Dispatchers.IO)
                .collect {
                }
        }
    }
}
