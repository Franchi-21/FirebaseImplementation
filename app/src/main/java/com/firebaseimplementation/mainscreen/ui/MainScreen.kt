package com.firebaseimplementation.mainscreen.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.firebaseimplementation.R
import com.firebaseimplementation.mainscreen.templates.CreateIcon
import com.firebaseimplementation.navigation.Routes

@Composable
fun MainScreen(navController: NavHostController) {
    Icons(navController = navController)
}

@Composable
fun Icons(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        CreateIcon(
            icon = R.drawable.add,
            description = "addUser",
            modifier = Modifier.clickable {
                navController.navigate(route = Routes.ADD_USER.tag)
            },
            title = "Add new user",
        )
        CreateIcon(
            icon = R.drawable.add_user,
            description = "showUsers",
            modifier = Modifier.clickable {
                navController.navigate(route = Routes.GET_USERS.tag)
            },
            title = "Show existent users",
        )
        CreateIcon(
            icon = R.drawable.edit,
            description = "editUser",
            modifier = Modifier.clickable {
                navController.navigate(route = Routes.EDIT_USER.tag)
            },
            title = "Edit users",
        )
        CreateIcon(
            icon = R.drawable.delete,
            description = "deleteUser",
            modifier = Modifier.clickable {
                navController.navigate(route = Routes.DEL_USER.tag)
            },
            title = "Delete users",
        )
    }
}
