package com.iban.foroindie.repository


import com.iban.foroindie.data.dao.UsuarioDao
import com.iban.foroindie.data.models.Usuario



class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun register(
        email: String,
        pass: String,
        nombre: String,
        fecha: String,
        telefono: String
    ): Result<Unit> {
        if (usuarioDao.exists(email)) {
            return Result.failure(Exception("El correo ya está registrado"))
        }

        val nuevoUsuario = Usuario(
            email = email,
            password = pass,
            nombre = nombre,
            fechaNacimiento = fecha,
            telefono = telefono
        )

        usuarioDao.insertUsuario(nuevoUsuario)
        return Result.success(Unit)
    }

    suspend fun login(email: String, password: String): Boolean {

        val user = usuarioDao.getByEmail(email) ?: return false

        return user.password == password
    }

    suspend fun actualizarDatos(email: String, nombre: String, fecha: String, telefono: String) {
        usuarioDao.actualizarDatos(email, nombre, fecha, telefono)
    }

    suspend fun obtenerUsuarioPorEmail(email: String): Usuario? {
        return usuarioDao.obtenerUsuarioPorEmail(email)
    }

}