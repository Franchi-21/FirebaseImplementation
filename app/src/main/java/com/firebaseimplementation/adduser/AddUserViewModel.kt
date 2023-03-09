package com.firebaseimplementation.adduser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebaseimplementation.adduser.data.AddUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserRepo: AddUserRepository,
) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name
    private val _age = MutableLiveData<String>()
    val age: LiveData<String> = _age
    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun onValueChange(name: String, age: String) {
        _name.value = name
        _age.value = age
        _isButtonEnabled.value = checkNameAndAge()
    }

    fun onSaveCredentials(name: String, age: Int) {
        viewModelScope.launch {
            addUserRepo.insertUser(name = name, age = age)
        }
    }

    private fun checkNameAndAge(): Boolean {
        val nameArray = _name.value!!.toCharArray()
        val ageArray = _age.value!!.toCharArray()
        return nameArray.all { !it.isDigit() } && ageArray.all { it.isDigit() }
    }
}
