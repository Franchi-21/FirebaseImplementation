package com.firebaseimplementation.editusers.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.firebaseimplementation.editusers.EditUsersViewModel

@Composable
fun EditUsers(editUsersVM: EditUsersViewModel) {
    val oldName by editUsersVM.oldName.observeAsState(initial = "")
    val name by editUsersVM.name.observeAsState(initial = "")
    val age by editUsersVM.age.observeAsState(initial = "")
    val isButtonEnabled by editUsersVM.isButtonEnabled.observeAsState(initial = false)
    val userExists by editUsersVM.userExists.observeAsState(initial = false)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        OldNamePrompt(oldName = oldName) {
            editUsersVM.onOldNameValueChange(name = it)
        }
        VerifyName(
            vm = editUsersVM,
            oldName = oldName,
            isButtonEnabled = isButtonEnabled,
        )
        Spacer(modifier = Modifier.size(15.dp))
        NewNamePrompt(newName = name, enabled = userExists) {
            editUsersVM.onValueChange(name = it, age = age)
        }
        NewAgePrompt(newAge = age, enabled = userExists) {
            editUsersVM.onValueChange(name = name, age = it)
        }
        UpdateUser(
            vm = editUsersVM,
            oldName = oldName,
            newName = name,
            newAge = age,
            isButtonEnabled = userExists,
        )
    }
}

@Composable
private fun NewNamePrompt(
    newName: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = newName,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(text = "Old name") },
        label = { Text(text = "Type the name") },
        enabled = enabled,
    )
}

@Composable
private fun NewAgePrompt(
    newAge: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = newAge,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(text = "Old name") },
        label = { Text(text = "Type the name") },
        enabled = enabled,
    )
}

@Composable
private fun OldNamePrompt(
    oldName: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = oldName,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(text = "Old name") },
        label = { Text(text = "Type the name") },
    )
}

@Composable
fun VerifyName(
    vm: EditUsersViewModel,
    oldName: String,
    isButtonEnabled: Boolean,
) {
    Button(
        onClick = { vm.exists(name = oldName) },
        enabled = isButtonEnabled,
        modifier = Modifier.padding(vertical = 10.dp),
    ) {
        Text(text = "Check if the name exists in the database")
    }
}

@Composable
fun UpdateUser(
    vm: EditUsersViewModel,
    oldName: String,
    newName: String,
    newAge: String,
    isButtonEnabled: Boolean,
) {
    Button(
        onClick = {
            vm.updateUser(
                name = newName,
                age = newAge.toInt(),
                oldName = oldName,
            )
        },
        enabled = isButtonEnabled,
        modifier = Modifier.padding(vertical = 10.dp),
    ) {
        Text(text = "Update")
    }
}
