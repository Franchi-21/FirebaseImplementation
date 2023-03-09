package com.firebaseimplementation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebaseimplementation.adduser.AddUserViewModel
import com.firebaseimplementation.adduser.ui.AddUser
import com.firebaseimplementation.login.LoginViewModel
import com.firebaseimplementation.login.ui.Login
import com.firebaseimplementation.mainscreen.ui.MainScreen
import com.firebaseimplementation.register.RegisterViewModel
import com.firebaseimplementation.register.ui.Register

@Composable
fun MainNavigation(
    loginVM: LoginViewModel,
    registerVM: RegisterViewModel,
    addUserVM: AddUserViewModel,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN.tag) {
        composable(route = Routes.LOGIN.tag) {
            Login(loginVM = loginVM, navController = navController)
        }
        composable(route = Routes.REGISTER.tag) {
            Register(registerVM = registerVM, navController = navController)
        }
        composable(route = Routes.MAIN_SCREEN.tag) {
            MainScreen(navController = navController, addVM = addUserVM)
        }
        composable(route = Routes.ADD_USER.tag) {
            AddUser(addVM = addUserVM)
        }
    }
}
