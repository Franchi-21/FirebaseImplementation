package com.firebaseimplementation.login.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firebaseimplementation.R
import com.firebaseimplementation.login.LoginViewModel
import com.firebaseimplementation.navigation.Routes
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun Login(loginVM: LoginViewModel, navController: NavHostController) {
    val email by loginVM.email.observeAsState(initial = "")
    val pass by loginVM.pass.observeAsState(initial = "")
    val isLoginEnabled by loginVM.isButtonEnabled.observeAsState(initial = false)
    var isPasswordShowed by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        EmailPrompt(email = email) {
            loginVM.onLoginChange(email = it, pass = pass)
        }
        PassPrompt(
            pass = pass,
            isPasswordShowed = isPasswordShowed,
            onIconButtonClick = { isPasswordShowed = it },
        ) {
            loginVM.onLoginChange(email = email, pass = it)
        }
        LoginButton(
            isLoginEnabled = isLoginEnabled,
            email = email,
            pass = pass,
            context = context,
        ) {
            loginVM.deleteCredentials()
            navController.navigate(route = Routes.MAIN_SCREEN.tag)
        }
        Spacer(modifier = Modifier.size(10.dp))
        CreateAccountNotice(navController = navController)
    }
}

@Composable
fun CreateAccountNotice(navController: NavHostController) {
    Row {
        Text(text = "Don't have an account?")
        Text(
            text = "Create one!",
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(route = Routes.REGISTER.tag) },
        )
    }
}

@Composable
fun EmailPrompt(
    email: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = email,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Type your email here") },
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
    )
}

@Composable
fun PassPrompt(
    pass: String,
    isPasswordShowed: Boolean,
    onIconButtonClick: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = pass,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Type your pass here") },
        placeholder = { Text(text = "Pass") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        modifier = Modifier.padding(vertical = 10.dp),
        trailingIcon = {
            val icon = if (isPasswordShowed) {
                ImageVector.vectorResource(id = R.drawable.visibility_off)
            } else {
                ImageVector.vectorResource(id = R.drawable.visibility)
            }

            IconButton(onClick = { onIconButtonClick(!isPasswordShowed) }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "showPass",
                )
            }
        },
        visualTransformation = if (isPasswordShowed) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
}

@Composable
fun LoginButton(
    isLoginEnabled: Boolean,
    email: String,
    pass: String,
    context: Context,
    actions: () -> Unit
) {
    Button(
        onClick = {
            val auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Successful", "Yes")
                        Toast.makeText(
                            context,
                            "Welcome!",
                            Toast.LENGTH_LONG,
                        ).show()
                        actions()
                    } else {
                        Log.d("Successful", "No")
                        Toast.makeText(
                            context,
                            "The entered user do not exist",
                            Toast.LENGTH_LONG,
                        ).show()
                    }
                }
        },
        enabled = isLoginEnabled,
    ) {
        Text(text = "Sign in")
    }
}
