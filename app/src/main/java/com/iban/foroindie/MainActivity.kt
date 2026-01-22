package com.iban.foroindie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Importa tu base de datos y repositorios
import com.iban.foroindie.data.db.AppDatabase
import com.iban.foroindie.repository.UsuarioRepository

// Importa tu tema
import com.iban.foroindie.ui.theme.ForoIndieTheme

// Importa tus pantallas y ViewModels
import com.iban.foroindie.ui.theme.screen.LoginRoute
import com.iban.foroindie.ui.theme.screen.RegisterRoute
import com.iban.foroindie.ui.theme.viewModel.UsuarioViewModel
import com.iban.foroindie.ui.theme.viewModel.UsuarioViewModelFactory
import com.iban.foroindie.vistas.MainAppContainer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForoIndieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current

                    val database = AppDatabase.getInstance(context)
                    // -----------------------

                    val repository = UsuarioRepository(database.usuarioDao())
                    val factory = UsuarioViewModelFactory(repository)

                    val usuarioViewModel: UsuarioViewModel = viewModel(factory = factory)

                    val mainNavController = rememberNavController()

                    AppNavGraph(
                        mainNavController = mainNavController,
                        viewModel = usuarioViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavGraph(
    mainNavController: NavHostController,
    viewModel: UsuarioViewModel
) {
    NavHost(
        navController = mainNavController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginRoute(
                viewModel = viewModel,
                onNavigateToRegister = { mainNavController.navigate("register") },
                onLoginSuccess = {
                    mainNavController.navigate("home_container") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "register") {
            RegisterRoute(
                viewModel = viewModel,
                onNavigateToLogin = { mainNavController.popBackStack() }
            )
        }


        composable(route = "home_container") {
            MainAppContainer(

                viewModel = viewModel,
                onLogout = {
                    mainNavController.navigate(route = "login") {
                        popUpTo(route = "home_container") { inclusive = true }
                    }
                }
            )
        }
        }
    }