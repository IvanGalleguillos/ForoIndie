package com.iban.foroindie.vistas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iban.foroindie.ui.theme.viewModel.UsuarioViewModel


sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Feed : BottomNavItem("feed", Icons.Filled.Home, "Inicio")
    object Profile : BottomNavItem("profile", Icons.Filled.Person, "Perfil")
}

@Composable
fun MainAppContainer(
    viewModel: UsuarioViewModel,
    onLogout: () -> Unit
) {

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = bottomNavController,
                startDestination = BottomNavItem.Feed.route
            ) {

                composable(BottomNavItem.Feed.route) {
                    FeedScreen(
                        onEntrarClick = { })
                }

                composable(BottomNavItem.Profile.route) {

                    ProfileScreen(
                        viewModel = viewModel,
                        onLogout = onLogout
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(BottomNavItem.Feed, BottomNavItem.Profile)

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}