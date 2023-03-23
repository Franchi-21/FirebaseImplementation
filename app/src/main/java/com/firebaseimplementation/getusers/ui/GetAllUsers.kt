package com.firebaseimplementation.getusers.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.firebaseimplementation.database.User
import com.firebaseimplementation.getusers.GetUsersViewModel

@Composable
fun GetAllUsers(getUserVM: GetUsersViewModel) {
    var isButtonPressed by remember { mutableStateOf(false) }
    val userList by getUserVM.showUserList.collectAsState()
    ShowUsersButton(isButtonPressed = isButtonPressed) {
        isButtonPressed = it
        getUserVM.getAllUsers()
    }
    if (isButtonPressed) {
        UserList(users = userList)
    }
}

@Composable
private fun UserList(users: List<User>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(users) { user ->
            UserCard(user = user)
        }
    }
}

@Composable
private fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .width(200.dp)
            .height(75.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffffa2c7),
            contentColor = Color.Black,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RectangleShape,
    ) {
        Text(
            text = user.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp),
        )
        Text(
            text = user.age.toString(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
fun ShowUsersButton(
    isButtonPressed: Boolean,
    onClick: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { onClick(!isButtonPressed) }) {
            Text(text = "Show all the users")
        }
    }
}
