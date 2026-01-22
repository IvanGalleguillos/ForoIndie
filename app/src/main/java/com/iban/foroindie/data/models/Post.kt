package com.iban.foroindie.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class Post(
    val id: Int,
    val usuario: String,
    val titulo: String,
    val descripcion: String,
    val likes: Int = 0
)