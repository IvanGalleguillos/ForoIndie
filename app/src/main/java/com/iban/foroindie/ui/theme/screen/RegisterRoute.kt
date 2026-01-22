package com.iban.foroindie.ui.theme.screen

import androidx.compose.runtime.Composable
import com.iban.foroindie.ui.theme.viewModel.UsuarioViewModel

@Composable
fun RegisterRoute(
    viewModel: UsuarioViewModel,
    onNavigateToLogin: () -> Unit
) {
    RegisterScreen(
        viewModel = viewModel,
        onNavigateToLogin = onNavigateToLogin
    )
}