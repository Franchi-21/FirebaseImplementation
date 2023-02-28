package com.firebaseimplementation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebaseimplementation.util.checkCredentials

class RegisterViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass
    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun onLoginChange(email: String, pass: String) {
        _email.value = email
        _pass.value = pass
        _isButtonEnabled.value = checkCredentials(email = email, pass = pass)
    }
}
