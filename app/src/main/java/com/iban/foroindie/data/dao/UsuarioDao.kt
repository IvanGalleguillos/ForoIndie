package com.iban.foroindie.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iban.foroindie.data.models.Usuario


@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUsuario(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): Usuario?

    @Query("SELECT EXISTS(SELECT 1 FROM usuarios WHERE email = :email)")
    suspend fun exists(email: String): Boolean

    @Query("UPDATE usuarios SET nombre = :nombre, fecha_nacimiento = :fecha, telefono = :telefono WHERE email = :email")
    suspend fun actualizarDatos(email: String, nombre: String, fecha: String, telefono: String)

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun obtenerUsuarioPorEmail(email: String): Usuario?
}