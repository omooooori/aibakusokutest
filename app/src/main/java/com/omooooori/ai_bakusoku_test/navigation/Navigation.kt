package com.omooooori.ai_bakusoku_test.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "ホーム", Icons.Default.Home)
    object Profile : Screen("profile", "プロフィール", Icons.Default.Person)
    object Settings : Screen("settings", "設定", Icons.Default.Settings)
    object Detail : Screen("detail/{userId}", "詳細", Icons.Default.Info)
}

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "ホーム", Icons.Default.Home)
    object Profile : BottomNavItem("profile", "プロフィール", Icons.Default.Person)
    object Settings : BottomNavItem("settings", "設定", Icons.Default.Settings)
}

data class UserDetailArgs(val userId: String) 