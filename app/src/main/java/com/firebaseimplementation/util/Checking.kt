package com.firebaseimplementation.util

import android.util.Patterns

/**
 * Checks if both [email] and [pass] are valid
 * @return true if both [email] and [pass] are valid, false if not */
fun checkCredentials(email: String, pass: String) =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length > 6
