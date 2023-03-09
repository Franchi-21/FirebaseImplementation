package com.firebaseimplementation.mainscreen.templates

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun CreateIcon(
    @DrawableRes icon: Int,
    description: String,
    modifier: Modifier
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = icon),
        contentDescription = description,
        modifier = modifier,
    )
}
