package com.firebaseimplementation.getusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebaseimplementation.database.User
import com.firebaseimplementation.getusers.data.database.GetUsersImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetUsersViewModel @Inject constructor(
    private val getUsersImpl: GetUsersImpl,
) : ViewModel() {
    private val _showUserList = MutableStateFlow<List<User>>(listOf())
    val showUserList: StateFlow<List<User>> = _showUserList

    fun getAllUsers() = viewModelScope.launch {
        getUsersImpl
            .getAllUsers()
            .flowOn(Dispatchers.IO)
            .collect {
                _showUserList.value = it
            }
    }
}
