package com.iban.foroindie.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "usuarios",
    indices = [Index(value = ["email"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,

    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,

    @ColumnInfo(name = "nombre") val nombre: String? = null,
    @ColumnInfo(name = "fecha_nacimiento") val fechaNacimiento: String? = null,
    @ColumnInfo(name = "telefono") val telefono: String? = null,

    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)