package com.firebaseimplementation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.firebaseimplementation.adduser.AddUserViewModel
import com.firebaseimplementation.editusers.EditUsersViewModel
import com.firebaseimplementation.getusers.GetUsersViewModel
import com.firebaseimplementation.login.LoginViewModel
import com.firebaseimplementation.navigation.MainNavigation
import com.firebaseimplementation.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginVM: LoginViewModel by viewModels()
    private val registerVM: RegisterViewModel by viewModels()
    private val addUserVM: AddUserViewModel by viewModels()
    private val getUserVM: GetUsersViewModel by viewModels()
    private val editUsersVM: EditUsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavigation(
                loginVM = loginVM,
                registerVM = registerVM,
                addUserVM = addUserVM,
                getUserVM = getUserVM,
                editUsersVM = editUsersVM,
            )
        }
    }
}
