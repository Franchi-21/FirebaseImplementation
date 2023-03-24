package com.firebaseimplementation.deleteusers.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.firebaseimplementation.deleteusers.DeleteUserViewModel

@Composable
fun DeleteUser(deleteUserVM: DeleteUserViewModel) {
    val name by deleteUserVM.nameToDelete.observeAsState(initial = "")
    val isButtonEnabled by deleteUserVM.isButtonEnabled.observeAsState(initial = false)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        UserToDelete(
            name = name,
            vm = deleteUserVM,
        )
        DeleteButton(
            vm = deleteUserVM,
            name = name,
            isButtonEnabled = isButtonEnabled,
        )
    }
}

@Composable
fun UserToDelete(
    name: String,
    vm: DeleteUserViewModel,
) {
    OutlinedTextField(
        value = name,
        onValueChange = { vm.onValueChange(it) },
        singleLine = true,
        label = { Text(text = "User to delete") },
        placeholder = { Text(text = "Input the user's name to delete it") },
    )
}

@Composable
fun DeleteButton(
    vm: DeleteUserViewModel,
    name: String,
    isButtonEnabled: Boolean,
) {
    Button(
        onClick = { vm.deleteByName(name = name) },
        modifier = Modifier.padding(vertical = 10.dp),
        enabled = isButtonEnabled,
    ) {
        Text(text = "Delete user")
    }
}
