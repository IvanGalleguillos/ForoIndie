package com.iban.foroindie.vistas // O el paquete donde la tengas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iban.foroindie.ui.theme.viewModel.UsuarioViewModel

@Composable
fun ProfileScreen(
    viewModel: UsuarioViewModel,
    onLogout: () -> Unit
) {

    val usuario = viewModel.usuarioActual

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mi Perfil", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        ProfileItem(label = "Nombre", valor = usuario?.nombre ?: "Cargando...")
        ProfileItem(label = "Email", valor = usuario?.email ?: "Cargando...")
        ProfileItem(label = "Fecha Nacimiento", valor = usuario?.fechaNacimiento ?: "--/--/----")
        ProfileItem(label = "Teléfono", valor = usuario?.telefono ?: "Sin número")

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.logout()
                onLogout()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Cerrar Sesión")
        }
    }
}

@Composable
fun ProfileItem(label: String, valor: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Text(text = valor, style = MaterialTheme.typography.bodyLarge)
        Divider(color = MaterialTheme.colorScheme.surfaceVariant)
    }
}