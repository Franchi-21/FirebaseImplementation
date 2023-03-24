package com.firebaseimplementation.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.firebaseimplementation.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(newUser: UserEntity)

    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM user_table WHERE name = :name")
    suspend fun deleteByName(name: String): Int

    @Query("UPDATE user_table SET name = :name, age = :age WHERE name = :oldName")
    suspend fun updateUser(name: String, age: Int, oldName: String)

    @Query("SELECT COUNT(1) FROM user_table WHERE name = :name")
    suspend fun exists(name: String): Int
}
