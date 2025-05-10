package com.example.aplicacionmovil_spacey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicacionmovil_spacey.ui.theme.AplicacionMovilSpaceYTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState


import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionmovil_spacey.ui.components.AppDrawer
import com.example.aplicacionmovil_spacey.ui.screens.HomeScreen
import com.example.aplicacionmovil_spacey.ui.screens.ProfileScreen
import com.example.aplicacionmovil_spacey.ui.screens.TasksScreen
import kotlinx.coroutines.launch
import androidx.compose.NavHost
import androidx.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicacionMovilSpaceYTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaConFondo(modifier = Modifier.padding(innerPadding))
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

                        TaskManagerApp()

                    }
                }

            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplicacionMovilSpaceYTheme {
        Greeting("Android")
    }
}

@Composable
fun PantallaConFondo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp), // Mantén el padding general

            verticalArrangement = Arrangement.SpaceBetween // Coloca el primero y el último en los extremos
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de la aplicación",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(bottom = 80.dp)
                )
            }

            // Este Spacer ocupará todo el espacio restante, empujando el texto hacia abajo
            Spacer(modifier = Modifier.weight(0.5f))
            // Texto en la parte inferior
            Text(
                text = "Adrián Sánchez Álvarez\n" +
                        "Manuel Saucedo Gonzalez\n" +
                        "Yeray Via Alba",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp, lineHeight = 16.sp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun TaskManagerApp() {

// navController: Controlador de navegación para movernos entre pantallas.

    // drawerState: Controla si el Navigation Drawer está abierto o cerrado (inicia cerrado).

//  scope: Un CoroutineScope para lanzar corutinas, necesario para operaciones asincrónicas
//  como abrir/cerrar el Drawer.

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

//    ModalNavigationDrawer: Es un Drawer que aparece como modal encima del contenido. Recibe:

//    - drawerState: para saber si está abierto o cerrado.

//   -  drawerContent: el contenido del Drawer (menú lateral).


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            //          AppDrawer: Es un composable que representa el menú lateral.
            //   Recibe una lambda onDestinationClicked que se ejecuta cuando el usuario
            //   hace clic en una opción del menú.
            AppDrawer(
                onDestinationClicked = { route ->
                    // Dentro de esa lambda:

                    // Se navega a la ruta (route) con navController.navigate(route) y se configura:

                    // - popUpTo(...) { inclusive = true }: limpia la pila de navegación hasta el inicio.

                    // - launchSingleTop = true: evita duplicar pantallas si ya están en la cima.

                    // Luego se cierra el Drawer con drawerState.close() dentro de una corutina.
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        //      Estos composables se renderizan en función de la ruta activa del navController.
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeScreen() }
            composable("tasks") { TasksScreen() }
            composable("profile") { ProfileScreen() }
        }
    }





}

