package com.firebaseimplementation.adduser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.firebaseimplementation.adduser.AddUserViewModel

@Composable
fun AddUser(addVM: AddUserViewModel) {
    val name by addVM.name.observeAsState(initial = "")
    val age by addVM.age.observeAsState(initial = "")
    val isButtonEnabled by addVM.isButtonEnabled.observeAsState(false)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        NamePrompt(name = name) {
            addVM.onValueChange(name = it, age = age)
        }
        AgePrompt(age = age) {
            addVM.onValueChange(name = name, age = it)
        }
        SendDataButton(
            isButtonEnabled = isButtonEnabled,
            name = name,
            age = age,
            saveUser = addVM,
        )
    }
}

@Composable
fun NamePrompt(
    name: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = name,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(text = "Name") },
        label = { Text(text = "Type their name here") },
    )
}

@Composable
fun AgePrompt(
    age: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = age,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(text = "Age") },
        label = { Text(text = "Type their age here") },
    )
}

@Composable
fun SendDataButton(
    name: String,
    age: String,
    isButtonEnabled: Boolean,
    saveUser: AddUserViewModel,
) {
    Button(
        onClick = { saveUser.onSaveCredentials(name = name, age = age.toInt()) },
        enabled = isButtonEnabled,
    ) {
        Text(text = "Add")
    }
}
