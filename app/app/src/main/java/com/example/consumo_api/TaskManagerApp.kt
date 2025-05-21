package com.example.consumo_api

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.consumo_api.ui.components.AppDrawer
import com.example.consumo_api.ui.screens.HomeScreen
import com.example.consumo_api.ui.screens.AnimalTabLayout
import com.example.consumo_api.ui.screens.TaskScreen
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.consumo_api.models.ViewModel_class
import com.example.consumo_api.ui.screens.SalaScreen
import com.example.consumo_api.ui.theme.Pink80
import com.example.consumo_api.modules.AnimalViewModel

@Composable
fun TaskManagerApp() {

// navController: Controlador de navegación para movernos entre pantallas.

 // drawerState: Controla si el Navigation Drawer está abierto o cerrado (inicia cerrado).

//  scope: Un CoroutineScope para lanzar corutinas, necesario para operaciones asincrónicas
//  como abrir/cerrar el Drawer.

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //MIO
    val viewModel: ViewModel_class = viewModel()

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
    )

    {
  //      Estos composables se renderizan en función de la ruta activa del navController.
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeScreen() }
            composable("tasks") { TaskScreen(viewModel) }
            composable("profile") {AnimalTabLayout(AnimalViewModel()) }
            composable("Sala") { SalaScreen() }
        }
    }
}