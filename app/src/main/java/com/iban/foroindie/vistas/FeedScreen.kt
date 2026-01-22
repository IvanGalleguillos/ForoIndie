package com.iban.foroindie.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iban.foroindie.data.models.Post

object PostData {
    val listaPosts = listOf(
        Post(1, "NachoxD", "Mi nuevo Setup", "Hola a todos, por fin terminé de armar mi PC con una RTX 3060. ¿Qué opinan de la gestión de cables?", 120),
        Post(2, "IndieDevJuan", "Avance de mi juego", "Estoy trabajando en un juego de plataformas pixel art. Aquí una captura del nivel 1.", 45),
        Post(3, "MariaJuana", "Nuevo Juego de pinball", "Alguien sabe cómo pasar el nivel de este juego?", 12),
        Post(4, "FanIndieRetro", "Encontre este viejo juego", "Encontre un juego viejo pero buenisimo .", 300),
        Post(5, "YoNoSe", "Nuevos lanzamientos Indie", "Esta semana salen al mercado 5 juegos indies muy esperados. Hilo abajo 👇", 89),
    )
}

@Composable
fun FeedScreen(onEntrarClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Text(
                    text = "Foro Indie",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            items(PostData.listaPosts) { post ->
                PostItem(post = post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(text = post.usuario, fontWeight = FontWeight.Bold)
                    Text(text = "Hace 2 horas", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }

                Spacer(Modifier.weight(1f))
                Icon(Icons.Default.MoreVert, contentDescription = "Opciones")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = post.titulo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = post.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${post.likes}")

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Comentar",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )
            }
        }
    }
}