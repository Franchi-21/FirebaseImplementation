package com.firebaseimplementation.deleteusers

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebaseimplementation.deleteusers.data.DeleteUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class DeleteUserViewModel @Inject constructor(
    private val deleteUserRepository: DeleteUserRepository,
    private val context: Context,
) : ViewModel() {
    private val _nameToDelete = MutableLiveData<String>()
    val nameToDelete: LiveData<String> = _nameToDelete
    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun onValueChange(name: String) {
        _nameToDelete.value = name
        _isButtonEnabled.value = checkIfNotNumber()
    }

    private fun checkIfNotNumber(): Boolean {
        val nameToDelete = _nameToDelete.value?.toCharArray()
        if (nameToDelete?.isNotEmpty() == true) {
            return nameToDelete.all {
                !it.isDigit()
            }
        }
        return false
    }

    fun deleteByName(name: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                deleteUserRepository.deleteByName(name = name)
            }
            if (result == 1) {
                Toast.makeText(
                    context,
                    "The user was deleted successfully",
                    Toast.LENGTH_LONG,
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "The user could not be deleted successfully",
                    Toast.LENGTH_LONG,
                ).show()
            }
        }
    }
}
