package com.firebaseimplementation.getusers.data.database

import com.firebaseimplementation.database.User
import com.firebaseimplementation.database.dao.UserDao
import com.firebaseimplementation.database.toUserDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsersImpl @Inject constructor(
    private val userDao: UserDao,
) : GetUsersHelper {
    override fun getAllUsers(): Flow<List<User>> {
        return flow {
            val users = userDao
                .getAllUsers()
                .map { user ->
                    user.toUserDomain()
                }
            emit(users)
        }
    }
}
