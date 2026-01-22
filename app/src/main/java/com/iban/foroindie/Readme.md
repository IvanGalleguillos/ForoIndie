• Título del proyecto: Foro Indie

• Descripción breve de la aplicación: Un blog de personas que comparten cosas sobre juegos indies, pueden registrarse, tener perfil con datos y hacer publicaciones.

• Objetivos de la aplicación: Un blog en el cual una persona pueda registrarse y ver publicaciones de otras personas acerca de los juegos indies

• Descripción de cada Composable implementado: características y funcionalidades


LoginScreen: Pantalla de acceso. Contiene campos de texto para correo y contraseña. Valida las credenciales contra la base de datos local y, si son correctas, navega al área principal actualizando el estado de sesión.

RegisterScreen: Formulario completo de registro. Solicita nombre, correo, contraseña, fecha de nacimiento y teléfono, Guardando los datos para mostrarlos en el perfil.

AppNavGraph: controlado de navegacion que define las rutas (`login`, `register`, `home_container`) y gestiona el paso de argumentos y el `ViewModel` compartido.

MainAppContainer: Contenedor principal que aloja la barra de navegación inferior (`BottomNavigationBar`) y el `Scaffold`. Se encarga de mostrar las pantallas internas (Feed o Perfil) según la pestaña seleccionada.

ProfileScreen: Muestra la información del usuario logueado en tiempo real (Nombre, Email, Teléfono, etc.). Incluye la funcionalidad de Cerrar Sesión, que limpia el estado del `ViewModel` y devuelve al usuario al Login.

FeedScreen: (En desarrollo) Pantalla de inicio destinada a mostrar el contenido principal del foro. Actualmente configurada como punto de entrada de la navegación interna.

• Requisitos de la aplicación: Android Studio, SDK 24 y librerias:
        -androidx.compose.
        -androidx.room.
        -androidx.navigation.

• Estructura del proyecto

• Notas/Consideraciones (mejoras posibles, limitaciones, etc):
Tuve mucho enredo con el programa, no me salia nada, aun haciendo los ejercicios en clase :( asi que tuve que ayudarme de la ia:(, asi que, para mas adelante lo ideal seria subir posts de verdad y mejorar la estructura general del proyecto.

• Autores: Ivan Galleguillos