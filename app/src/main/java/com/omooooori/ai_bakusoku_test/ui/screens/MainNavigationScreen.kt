package com.omooooori.ai_bakusoku_test.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.omooooori.ai_bakusoku_test.MainViewModel
import com.omooooori.ai_bakusoku_test.navigation.BottomNavItem
import com.omooooori.ai_bakusoku_test.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                val bottomNavItems = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Profile,
                    BottomNavItem.Settings
                )
                
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                // 同じ画面に複数回ナビゲートすることを防ぐ
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { userId ->
                        navController.navigate(Screen.Detail.route.replace("{userId}", userId))
                    }
                )
            }
            
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
            
            composable(Screen.Detail.route) { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId") ?: "1"
                DetailScreen(
                    userId = userId,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
} 