package com.iban.foroindie.ui.theme.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iban.foroindie.data.db.AppDatabase
import com.iban.foroindie.repository.UsuarioRepository
import com.iban.foroindie.ui.theme.viewModel.UsuarioViewModel

@Composable
fun LoginRoute(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: UsuarioViewModel
) {
    val context = LocalContext.current


    val repository = remember(context) {
        val db = AppDatabase.getInstance(context)
        val dao = db.usuarioDao()
        UsuarioRepository(dao)
    }


    val factory = remember(repository) {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UsuarioViewModel(repository) as T
            }
        }
    }

    val vm: UsuarioViewModel = viewModel(factory = factory)

    var loginMessage by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    LoginScreen(
        onLogin = { email, password ->
            loading = true
            vm.login(email, password) { ok ->
                loading = false
                if (ok) {
                    loginMessage = "Login correcto"
                    onLoginSuccess()
                } else {
                    loginMessage = "Credenciales inválidas"
                }
            }
        },
        onNavigateToRegister = onNavigateToRegister,
        loginResultMessage = if (loading) "Validando..." else loginMessage
    )
}