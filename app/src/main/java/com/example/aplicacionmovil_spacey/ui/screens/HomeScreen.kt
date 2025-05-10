package com.example.ejemplo_menu_drawer.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


// Habilita el uso de una API que aún está marcada como experimental
// en una librería, en este caso Material 3 de Jetpack Compose.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
 //   Scaffold es un layout de alto nivel que proporciona una estructura básica para pantallas,
 //   permitiendo colocar fácilmente elementos como barra superior, inferior, FAB, Drawer, etc.
//   Aquí se usa únicamente la topBar, configurada con un TopAppBar
    Scaffold(
  //      TopAppBar: es la barra superior de la pantalla.
//      title = { Text("Inicio") }: muestra el texto "Inicio" como título en la barra.
        topBar = { TopAppBar(title = { Text("Inicio") }) }
    ) { paddingValues ->
  //      El bloque después del paréntesis del Scaffold es el slot de contenido principal.
  //      Recibe paddingValues, que son los márgenes que hay que respetar para no solaparse
  //      con la TopAppBar.

 //       Se usa un Box como contenedor (permite apilar elementos), con dos modificadores:

 //       fillMaxSize(): hace que el Box ocupe todo el espacio disponible.

  //      padding(paddingValues): aplica el padding que dejó libre la TopAppBar.

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            Text("Bienvenido a la app de tareas")
        }
    }
}