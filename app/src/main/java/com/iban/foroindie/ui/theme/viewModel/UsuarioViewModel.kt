package com.iban.foroindie.ui.theme.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iban.foroindie.data.models.Usuario
import com.iban.foroindie.repository.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    var usuarioActual by mutableStateOf<Usuario?>(null)
        private set

    fun login(email: String, pass: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {

            val usuarioEncontrado = repository.obtenerUsuarioPorEmail(email)

            if (usuarioEncontrado != null && usuarioEncontrado.password == pass) {
                usuarioActual = usuarioEncontrado
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    fun register(
        email: String,
        pass: String,
        nombre: String,
        fecha: String,
        telefono: String,
        onResult: (Result<Unit>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                repository.register(email, pass, nombre, fecha, telefono)

                usuarioActual = Usuario(
                    nombre = nombre,
                    email = email,
                    password = pass,
                    fechaNacimiento = fecha,
                    telefono = telefono
                )
                // -----------------------------

                onResult(Result.success(Unit))
            } catch (e: Exception) {
                onResult(Result.failure(e))
            }
        }
    }

    fun logout() {
        usuarioActual = null
    }
}