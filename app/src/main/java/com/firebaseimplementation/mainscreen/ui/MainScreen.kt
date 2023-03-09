package com.firebaseimplementation.mainscreen.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.firebaseimplementation.R
import com.firebaseimplementation.adduser.AddUserViewModel
import com.firebaseimplementation.mainscreen.templates.CreateIcon
import com.firebaseimplementation.navigation.Routes

@Composable
fun MainScreen(navController: NavHostController, addVM: AddUserViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Icons(navController = navController)
        LazyColumn {
        }
    }
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
        )
        CreateIcon(
            icon = R.drawable.edit,
            description = "editUser",
            modifier = Modifier.clickable {
                navController.navigate(route = Routes.EDIT_USER.tag)
            },
        )
        CreateIcon(
            icon = R.drawable.delete,
            description = "deleteUser",
            modifier = Modifier.clickable {
                navController.navigate(route = Routes.DEL_USER.tag)
            },
        )
    }
}
