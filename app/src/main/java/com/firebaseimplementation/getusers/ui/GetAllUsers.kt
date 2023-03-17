package com.firebaseimplementation.getusers.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.firebaseimplementation.database.User
import com.firebaseimplementation.getusers.GetUsersViewModel

@Composable
fun GetAllUsers(getUserVM: GetUsersViewModel) {
    var isButtonPressed by remember { mutableStateOf(false) }
    val userList by getUserVM.showUserList.collectAsState()
    if (isButtonPressed) {
        UserList(users = userList)
    }
    ShowUsersButton(isButtonPressed = isButtonPressed) {
        isButtonPressed = it
        getUserVM.getAllUsers()
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(users) { user ->
            UserCard(user = user)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(user: User) {
    Card {
        Text(text = user.name)
        Text(text = user.age.toString())
    }
}

@Composable
fun ShowUsersButton(
    isButtonPressed: Boolean,
    onClick: (Boolean) -> Unit,
) {
    Button(onClick = { onClick(!isButtonPressed) }) {
        Text(text = "Show all the users")
    }
}
