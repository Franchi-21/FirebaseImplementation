package com.firebaseimplementation.editusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebaseimplementation.editusers.data.database.EditUsersImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditUsersViewModel @Inject constructor(
    private val editUserImpl: EditUsersImpl,
) : ViewModel() {
    private val _oldName = MutableLiveData<String>()
    val oldName: LiveData<String> = _oldName
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name
    private val _age = MutableLiveData<String>()
    val age: LiveData<String> = _age
    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled
    private val _userExists = MutableLiveData<Boolean>()
    val userExists: LiveData<Boolean> = _userExists

    fun onOldNameValueChange(name: String) {
        _oldName.value = name
        _isButtonEnabled.value = checkIfNotNumber()
    }

    private fun checkIfNotNumber(): Boolean {
        val oldName = _oldName.value?.toCharArray()
        if (oldName?.isNotEmpty() == true) {
            return oldName.all {
                !it.isDigit()
            }
        }
        return false
    }

    fun onValueChange(name: String, age: String) {
        _name.value = name
        _age.value = age
    }

    fun exists(name: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                editUserImpl.exists(name = name)
            }
            if (result == 1) {
                _userExists.value = true
            }
        }
    }

    fun updateUser(
        name: String,
        age: Int,
        oldName: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            editUserImpl.updateUser(
                name = name,
                age = age,
                oldName = oldName,
            )
        }
    }
}
