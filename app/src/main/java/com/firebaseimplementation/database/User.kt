package com.firebaseimplementation.database

import com.firebaseimplementation.database.entities.UserEntity

data class User(
    val name: String,
    val age: Int
)

fun UserEntity.toUserDomain() = User(name = name, age = age)
