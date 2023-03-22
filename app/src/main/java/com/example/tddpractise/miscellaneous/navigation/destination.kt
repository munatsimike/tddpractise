package com.example.tddpractise.miscellaneous.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class destination(
    val destination: String,
    val route: String,
    val icon: ImageVector
) {
    object Home : destination(
        destination = "Home",
        route = "home",
        icon = Icons.Default.Home
    )

    object Detail : destination(
        destination = "Detail",
        route = "detail",
        icon = Icons.Default.Home
    )
}